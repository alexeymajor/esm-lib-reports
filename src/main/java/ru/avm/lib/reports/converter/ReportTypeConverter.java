package ru.avm.lib.reports.converter;

public interface ReportTypeConverter {
    Object convert(Class<?> type, String[] values);

    String myType();
}
