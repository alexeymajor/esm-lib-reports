package ru.avm.lib.reports.converter;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@RequiredArgsConstructor
@ToString
@Getter
public class RelativePeriod {
    private final Integer days;
    private final Integer months;
    private final Integer weeks;
    private final Integer years;

    @SuppressWarnings("SameParameterValue")
    private static boolean charMatch(CharSequence text, int start, int end, char c) {
        return (start >= 0 && end == start + 1 && text.charAt(start) == c);
    }

    public LocalDate updateDate(LocalDate date) {
        if (years != null) {
            date = date.withDayOfYear(1).plusYears(years);
        }
        if (months != null) {
            date = date.withDayOfMonth(1).plusMonths(months);
        }

        if (weeks != null) {
            date = date.with(TemporalAdjusters.previousOrSame(WeekFields.ISO.getFirstDayOfWeek())).plusWeeks(weeks);
        }

        if (days != null) {
            date = date.plusDays(days);
        }

        return date;
    }

    public LocalDateTime updateTime(LocalDateTime time) {
        val date = updateDate(time.toLocalDate());
        return LocalDateTime.of(date, time.toLocalTime());
    }

    private static Integer parseNumber(CharSequence text, int start, int end, int negate) {
        if (start < 0 || end < 0) {
            return null;
        }
        int val = Integer.parseInt(text, start, end, 10);
        try {
            return Math.multiplyExact(val, negate);
        } catch (ArithmeticException ex) {
            throw new DateTimeParseException("Text cannot be parsed to a Period", text, 0, ex);
        }
    }

    private static final Pattern PATTERN =
            Pattern.compile("([-+]?)P(?:([-+]?\\d+)Y)?(?:([-+]?\\d+)M)?(?:([-+]?\\d+)W)?(?:([-+]?\\d+)D)?", Pattern.CASE_INSENSITIVE);

    public static RelativePeriod parse(CharSequence text) {
        Objects.requireNonNull(text, "text");
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches()) {
            int negate = (charMatch(text, matcher.start(1), matcher.end(1), '-') ? -1 : 1);
            int yearStart = matcher.start(2), yearEnd = matcher.end(2);
            int monthStart = matcher.start(3), monthEnd = matcher.end(3);
            int weekStart = matcher.start(4), weekEnd = matcher.end(4);
            int dayStart = matcher.start(5), dayEnd = matcher.end(5);
            try {
                Integer years = parseNumber(text, yearStart, yearEnd, negate);
                Integer months = parseNumber(text, monthStart, monthEnd, negate);
                Integer weeks = parseNumber(text, weekStart, weekEnd, negate);
                Integer days = parseNumber(text, dayStart, dayEnd, negate);
                return RelativePeriod.builder()
                        .years(years)
                        .months(months)
                        .weeks(weeks)
                        .days(days)
                        .build();

            } catch (NumberFormatException ex) {
                throw new DateTimeParseException("Text cannot be parsed to a Period", text, 0, ex);
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Period", text, 0);
    }


}
