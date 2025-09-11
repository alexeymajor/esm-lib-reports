package ru.avm.lib.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor

public class ReportDto {
    Long id;
    String title;
    String text;
    String icon;
    String color;
    String whereClause;
    String entity;
    Boolean isNative;
    List<ReportFieldDto> fields;
    List<ReportFilterDto> filters;
    Integer position;
}
