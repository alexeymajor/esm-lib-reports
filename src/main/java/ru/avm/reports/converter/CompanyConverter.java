package ru.avm.reports.converter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import ru.avm.common.CompaniesProxy;
import ru.avm.common.dto.CompanyDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Component
@ConditionalOnBean(CompaniesProxy.class)
public class CompanyConverter implements ReportTypeConverter {

    private static final String TYPE = "Company[]";
    private final CompaniesProxy companiesProxy;

    @Override
    public Object convert(Class<?> type, String... values) {
        val companyIds = Arrays.stream(values)
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return companyIds.stream().map(companiesProxy::findCompanyChildren)
                .flatMap(Collection::stream)
                .map(CompanyDto::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
