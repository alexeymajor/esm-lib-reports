package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor

public class ReportDto {
    Long id;
    String title;
    String text;
}
