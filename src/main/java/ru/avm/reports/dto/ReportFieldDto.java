package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ReportFieldDto {
    Long id;
    String title;
    String description;
    String term;
    String field;
    String groupTerm;
    String total;
    String type;
    Integer place;
    @Builder.Default
    Boolean visible = true;
}

