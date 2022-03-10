package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

@Component
public class BooleanConverter implements ReportTypeConverter {

    private static final String TYPE = "Boolean";

    @Override
    public Object convert(Class<?> type, String... values) {
        try {
            return Boolean.valueOf(values[0]);
        } catch (Exception ignore) {
            return !"0".equals(values[0]);
        }
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
