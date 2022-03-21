package ru.avm.reports;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.avm.reports.converter.DefaultConverter;
import ru.avm.reports.converter.ReportTypeConverter;
import ru.avm.reports.domain.Report;
import ru.avm.reports.domain.ReportField;
import ru.avm.reports.domain.ReportFilter;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.dto.ReportFieldDto;
import ru.avm.reports.dto.ReportResultDto;
import ru.avm.reports.repository.ReportFieldRepository;
import ru.avm.reports.repository.ReportFilterRepository;
import ru.avm.reports.repository.ReportRepository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor

@Service
@Transactional
public class ReportService {

    private final EntityManager entityManager;
    private final DefaultConverter defaultConverter;

    private final Map<String, ReportTypeConverter> converters;

    private final ReportFilterRepository reportFilterRepository;
    private final ReportFieldRepository reportFieldRepository;
    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    public List<ReportDto> allReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReportFieldDto> reportFields(Long reportId) {
        return reportRepository.findById(reportId)
                .map(report -> report.getFields().stream()
                        .map(reportMapper::toDto)
                        .collect(Collectors.toList()))
                .orElseThrow();
    }

    public ReportDto report(Long id) {
        return reportRepository.findById(id).map(reportMapper::toDto).orElseThrow();
    }

    @SneakyThrows
    public ReportResultDto runReport(Long reportId, List<Long> fields, Map<Long, String[]> filters) {

        if (fields.size() == 0) {
            throw new Exception("no fields selected for report");
        }

        val report = reportRepository.findById(reportId)
                .orElseThrow(() -> new Exception("report not found"));

        val reportFields = reportFieldRepository.findAllById(fields).stream()
                .sorted(Comparator.comparing(value -> fields.indexOf(value.getId())))
                .collect(Collectors.toList());

        val reportFilters = reportFilterRepository.findAllById(filters.keySet());

        val query = report.getIsNative() ? createNativeReport(report, reportFields, reportFilters)
                : createQuery(report, reportFields, reportFilters);

        reportFilters.forEach(reportFilter -> {
            val param = query.getParameter("p" + reportFilter.getId());

            val type = param.getParameterType();

            query.setParameter("p" + reportFilter.getId(),
                    getParameter(reportFilter, type, filters.get(reportFilter.getId())));
        });

        val data = query
//                .unwrap(org.hibernate.Query.class)
//                .setResultTransformer(
//                        new ResultTransformer() {
//                            @Override
//                            public Object transformTuple(Object[] tuple, String[] aliases) {
//                                return tuple;
//                            }
//
//                            @Override
//                            public List<Object[]> transformList(List collection) {
//                                //noinspection unchecked
//                                return collection;
//                            }
//                        })
                .getResultList();

        //noinspection unchecked
        return ReportResultDto.builder()
                .data(data)
                .fields(reportFields.stream().map(reportMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public void registerConverter(String type, ReportTypeConverter converter) {
        converters.put(type, converter);
    }

    @SneakyThrows
    private javax.persistence.Query createNativeReport(Report report, List<ReportField> fields, List<ReportFilter> filters) {

        val select = fields.stream()
                .map(reportField -> reportField.getTerm() + Optional.ofNullable(reportField.getField())
                        .filter(sid -> !sid.isBlank())
                        .filter(sid -> !sid.equals(reportField.getTerm()))
                        .map(sid -> " as " + sid)
                        .orElse(""))
                .collect(Collectors.toList());

        val groupBy = fields.stream()
                .map(ReportField::getGroupTerm)
                .filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());

        return entityManager.createNativeQuery(
                "select " + String.join(",", select) +
                        " from " + report.getEntity() +

                        getWhere(report, filters) +

                        (groupBy.size() == 0 ? "" : " group by " + String.join(",", groupBy)) +

                        " order by " +
                        IntStream.range(1, fields.size() + 1)
                                .mapToObj(Objects::toString)
                                .collect(Collectors.joining(",")), Tuple.class);
    }

    private String getWhere(Report report, List<ReportFilter> filters) {

        val filtersString = Optional.of(
                        filters.stream()
                                .map(reportFilter -> reportFilter.getClause()
                                        .replace("?", ":p" + reportFilter.getId()))
                                .collect(Collectors.joining(" and ")))
                .filter(sid -> !sid.isBlank());

        return Optional.ofNullable(report.getWhereClause())
                .filter(sid -> !sid.isBlank())
                .map(where -> filtersString
                        .map(filter -> " where " + where + " and " + filter)
                        .orElse(" where " + where))
                .orElse(filtersString.map(sid -> " where " + sid).orElse(""));
    }

    @SneakyThrows
    private javax.persistence.Query createQuery(Report report, List<ReportField> fields, List<ReportFilter> filters) {

        val select = fields.stream()
                .map(reportField -> reportField.getTerm() + " as " + reportField.getField())
                .collect(Collectors.toList());

        val groupBy = fields.stream()
                .map(ReportField::getGroupTerm)
                .filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());

        return entityManager.createQuery(
                "select " + String.join(",", select) +
                        " from " + report.getEntity() + " e" + getWhere(report, filters) +
                        (groupBy.size() == 0 ? "" : " group by " + String.join(",", groupBy)) +

                        " order by " +
                        IntStream.range(1, fields.size() + 1)
                                .mapToObj(Objects::toString)
                                .collect(Collectors.joining(",")), Tuple.class);
    }

    private Object getParameter(ReportFilter reportFilter, Class<?> parameterType, String... values) {
        return converters.getOrDefault(reportFilter.getParameterType(), defaultConverter)
                .convert(parameterType, values);
    }

}

