package ru.avm.lib.reports.converter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.avm.lib.common.CompaniesProxy;
import ru.avm.lib.common.dto.CompanyDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Component
public class CompanyConverter implements ReportTypeConverter {

    private static final String TYPE = "Company[]";
    private final CompaniesProxy companiesProxy;

    @Override
    public List<Long> convert(Class<?> type, String... values) {
        val companyIds = Arrays.stream(values)
                .map(Long::valueOf)
                .toList();

        return companyIds.stream().map(companiesProxy::findCompanyChildren)
                .flatMap(Collection::stream)
                .map(CompanyDto::id)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String myType() {
        return TYPE;
    }
}
