package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder

public class ReportFieldDto {
    Long id;
    String title;
    String description;
    String field;
    String total;
    String type;
}

