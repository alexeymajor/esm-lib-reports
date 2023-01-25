package ru.avm.reports;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.avm.reports.converter.DefaultConverter;
import ru.avm.reports.converter.ReportTypeConverter;
import ru.avm.reports.repository.ReportRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Configuration
@ComponentScan(basePackageClasses = {ReportTypeConverter.class})
public class ReportConfig {

    private final EntityManager entityManager;
    private final List<ReportTypeConverter> converters;
    private final ReportRepository reportRepository;

    @Bean
    public Map<String, ReportTypeConverter> converterMap() {
        return converters.stream().collect(Collectors.toMap(ReportTypeConverter::myType, Function.identity()));
    }
    @Bean
    public ReportMapper reportMapper() {
        return Mappers.getMapper(ReportMapper.class);
    }

    @Bean
    public ReportService reportService() {
        val defaultConverter = new DefaultConverter();
        return ReportService.builder()
                .converters(converterMap())
                .defaultConverter(defaultConverter)
                .entityManager(entityManager)
                .reportRepository(reportRepository)
                .reportMapper(reportMapper())
                .build();
    }
}
