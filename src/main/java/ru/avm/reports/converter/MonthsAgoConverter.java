package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MonthsAgoConverter implements ReportTypeConverter {

    private static final String TYPE = "MonthsAgo";

    @Override
    public LocalDateTime convert(Class<?> type, String... values) {
        return LocalDate.now().withDayOfMonth(1).minusMonths(Long.parseLong(values[0])).atStartOfDay();
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
