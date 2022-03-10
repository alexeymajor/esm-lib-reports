package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class ReportResultDto {
    List<ReportFieldDto> fields;
    List<Object[]> data;
}
