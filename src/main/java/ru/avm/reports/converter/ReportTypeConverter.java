package ru.avm.reports.converter;

public interface ReportTypeConverter {
    Object convert(Class<?> type, String[] values);

    String myType();
}
