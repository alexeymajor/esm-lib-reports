package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ReportResultDto {
    List<ReportFieldDto> fields;
    List<Object[]> data;
}
