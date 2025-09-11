package ru.avm.lib.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ReportFilterDto {
    Long id;
    String required;
    String parameter;
    String description;
    String clause;
    String type;
    Object expression;
    Integer place;
    @Builder.Default
    Boolean visible = true;

}
