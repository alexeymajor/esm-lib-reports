package ru.avm.reports.converter;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class RelativePeriodConverterTest {

    @Test
    void name() {
        val converter = new RelativePeriodConverter();
        val result = converter.convert(LocalDateTime.class, "-2D", "0M");
        System.out.println(result);
    }
}