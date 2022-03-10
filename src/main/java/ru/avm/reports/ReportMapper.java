package ru.avm.reports;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.avm.reports.domain.Report;
import ru.avm.reports.domain.ReportField;
import ru.avm.reports.domain.ReportFilter;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.dto.ReportFieldDto;
import ru.avm.reports.dto.ReportFilterDto;

@Mapper
public interface ReportMapper {
    ReportFieldDto toDto(ReportField domain);

    @Mapping(target = "type", ignore = true)
    ReportFilterDto toDto(ReportFilter domain);

    ReportDto toDto(Report domain);
}
