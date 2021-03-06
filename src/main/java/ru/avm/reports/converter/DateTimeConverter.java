package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateTimeConverter implements ReportTypeConverter {

    private static final String TYPE = "DateTime";

    @Override
    public LocalDateTime convert(Class<?> type, String[] values) {
        try {
            return LocalDateTime.parse(values[0]);
        } catch (Exception ignore) {
            return LocalDate.parse(values[0]).atStartOfDay();
        }
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
