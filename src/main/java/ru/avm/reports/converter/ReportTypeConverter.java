package ru.avm.reports.converter;

public interface ReportTypeConverter {
    Object convert(Class<?> type, String[] value);

    String myType();
}
