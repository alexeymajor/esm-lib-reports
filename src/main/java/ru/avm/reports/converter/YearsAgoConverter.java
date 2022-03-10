package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class YearsAgoConverter implements ReportTypeConverter {

    private static final String TYPE = "YearsAgo";

    @Override
    public LocalDateTime convert(Class<?> type, String... values) {
        return LocalDate.now().withDayOfYear(1).minusYears(Long.parseLong(values[0])).atStartOfDay();
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
