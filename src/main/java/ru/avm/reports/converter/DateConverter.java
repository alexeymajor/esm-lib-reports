package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter implements ReportTypeConverter {

    private static final String TYPE = "Date";

    @Override
    public LocalDate convert(Class<?> type, String... values) {
        return LocalDate.parse(values[0], DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
