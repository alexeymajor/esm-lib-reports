package ru.avm.reports.converter;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateConverter implements ReportTypeConverter {

    private static final String TYPE = "Date";

    @Override
    public Object convert(Class<?> type, String... values) {
        val result = LocalDate.parse(values[0]);
        if (type.equals(LocalDate.class)) {
            return result;
        }
        return result.atStartOfDay();
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
