package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

@Component
public class NumberConverter implements ReportTypeConverter {

    private static final String TYPE = "Number";

    @Override
    public Object convert(Class<?> type, String... values) {
        return Long.valueOf(values[0]);
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
