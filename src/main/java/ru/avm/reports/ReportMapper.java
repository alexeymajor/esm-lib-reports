package ru.avm.reports;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.avm.reports.domain.Report;
import ru.avm.reports.domain.ReportField;
import ru.avm.reports.domain.ReportFilter;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.dto.ReportFieldDto;
import ru.avm.reports.dto.ReportFilterDto;

@Mapper
public interface ReportMapper {

    @Mapping(target = "reportId", ignore = true)
    ReportField toDomain(ReportFieldDto dto);

    @Mapping(target = "reportId", ignore = true)
    ReportFilter toDomain(ReportFilterDto dto);

    @Mapping(target = "filtersVisible", ignore = true)
    @Mapping(target = "fieldsVisible", ignore = true)
    Report toDomain(ReportDto dto);

    @Mapping(target = "filtersVisible", ignore = true)
    @Mapping(target = "fieldsVisible", ignore = true)
    void updateDomain(@MappingTarget Report domain, ReportDto dto);

    @Named("full")
    ReportFieldDto toDto(ReportField domain);

    @Named("brief")
    @Mapping(target = "term", ignore = true)
    @Mapping(target = "groupTerm", ignore = true)
    @Mapping(target = "visible", ignore = true)
    ReportFieldDto toDtoBrief(ReportField domain);

    ReportFilterDto toDto(ReportFilter domain);

    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "filters", ignore = true)
    @Mapping(target = "isNative", ignore = true)
    @Mapping(target = "entity", ignore = true)
    @Mapping(target = "whereClause", ignore = true)
    ReportDto toDtoBrief(Report domain);

    @Mapping(target = "fields", qualifiedByName = "full")
    ReportDto toDtoFull(Report domain);

    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "filters", ignore = true)
    ReportDto toDtoListFull(Report domain);

    @Mapping(target = "isNative", ignore = true)
    @Mapping(target = "entity", ignore = true)
    @Mapping(target = "whereClause", ignore = true)
    @Mapping(target = "fields", source = "fieldsVisible", qualifiedByName = "brief")
    @Mapping(target = "filters", source = "filtersVisible")
    ReportDto toDto(Report domain);
}
