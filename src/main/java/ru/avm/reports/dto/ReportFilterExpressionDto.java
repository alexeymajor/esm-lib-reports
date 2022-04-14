package ru.avm.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ReportFilterExpressionDto {
    String type;
    String path;
    String idKey;
    String nameKey;
    String formikId;
    boolean multiple;
}
