package ru.avm.lib.reports.converter;

import org.springframework.stereotype.Component;

@Component
public class DateToDateTimeConverter extends DateTimeConverter {

    @Override
    public String myType() {
        return "DateToDateTime";
    }
}
