package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class NumberArrayConverter implements ReportTypeConverter {

    private static final String TYPE = "Number[]";

    @Override
    public Object convert(Class<?> type, String... values) {
        return Arrays.stream(values).map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
