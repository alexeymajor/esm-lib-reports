package ru.avm.reports.converter;

public class DefaultConverter implements ReportTypeConverter {
    @Override
    public Object convert(Class<?> type, String... values) {
        return values[0];
    }

    @Override
    public String myType() {
        return null;
    }

}
