package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder

public class ReportFilterDto {
    Long id;
    String description;
    String required;
    String clause;
    String type;
    Object expression;

}
