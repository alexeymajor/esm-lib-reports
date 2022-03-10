package ru.avm.reports.converter;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class RelativePeriodConverter implements ReportTypeConverter {

    private static final String TYPE = "Period";

    public LocalDateTime convert(LocalDateTime baseTime, String value) {
        val durationStart = value.indexOf("T");
        if (durationStart < 0) {
            val period = RelativePeriod.parse("P" + value);
            return period.updateTime(baseTime.toLocalDate().atStartOfDay());
        }

        if (durationStart == 0) {
            val duration = Duration.parse("P" + value);
            return baseTime.plus(duration);
        }

        val period = RelativePeriod.parse("P" + value.substring(0, durationStart));
        val duration = Duration.parse("P" + value.substring(durationStart));

        return period.updateTime(baseTime.toLocalDate().atStartOfDay()).plus(duration);
    }

    @Override
    public Object convert(Class<?> type, String... values) {
        val result = convert(LocalDateTime.now(), values[0]);
        if (type.equals(LocalDate.class)) {
            return result.toLocalDate();
        }
        return result;
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
