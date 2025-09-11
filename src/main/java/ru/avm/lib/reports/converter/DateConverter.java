package ru.avm.lib.reports.converter;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateConverter implements ReportTypeConverter {

    private static final String TYPE = "Date";

    @Override
    public Object convert(Class<?> type, String... values) {
        val result = LocalDate.parse(values[0]);
        if (LocalDateTime.class.equals(type)) {
            return result.atStartOfDay();
        }
        return result;
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
