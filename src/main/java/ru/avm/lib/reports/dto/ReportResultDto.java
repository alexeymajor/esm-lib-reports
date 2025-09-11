package ru.avm.lib.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Duration;
import java.util.List;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ReportResultDto {
    List<ReportFieldDto> fields;
    Duration execution;
    Duration fetching;
    List<Object[]> data;
}
