package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DateToDateTimeNextConverter implements ReportTypeConverter {

    @Override
    public LocalDateTime convert(Class<?> type, String[] values) {
        try {
            return LocalDateTime.parse(values[0]).toLocalDate().plus(1, ChronoUnit.DAYS).atStartOfDay();
        } catch (Exception ignore) {
            return LocalDate.parse(values[0]).plus(1, ChronoUnit.DAYS).atStartOfDay();
        }
    }

    @Override
    public String myType() {
        return "DateToDateTimeNext";
    }
}
