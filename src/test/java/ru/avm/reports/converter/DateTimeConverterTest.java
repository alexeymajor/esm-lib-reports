package ru.avm.reports.converter;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeConverterTest {

    @Test
    void name() {
        val dateTimeStr = "2022-05-01T01:00";
        val dt = LocalDateTime.parse(dateTimeStr);
        System.out.println(dt);
    }
}