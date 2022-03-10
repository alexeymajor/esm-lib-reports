package ru.avm.reports.converter;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class WeeksAgoConverter implements ReportTypeConverter {

    private static final String TYPE = "WeeksAgo";

    @Override
    public LocalDateTime convert(Class<?> type, String... values) {
        return LocalDate.now().with(DayOfWeek.MONDAY).minusWeeks(Long.parseLong(values[0])).atStartOfDay();
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
