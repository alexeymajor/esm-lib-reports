package ru.avm.lib.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateToDateTimeNextConverter implements ReportTypeConverter {

    @Override
    public LocalDateTime convert(Class<?> type, String[] values) {
        try {
            return LocalDateTime.parse(values[0]).toLocalDate().plusDays(1).atStartOfDay();
        } catch (Exception ignore) {
            return LocalDate.parse(values[0]).plusDays(1).atStartOfDay();
        }
    }

    @Override
    public String myType() {
        return "DateToDateTimeNext";
    }
}
