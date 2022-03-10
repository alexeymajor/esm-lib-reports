package ru.avm.reports;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.avm.reports.domain.Report;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.dto.ReportResultDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping("reports")
public class ReportController {

    private final ReportService reportService;

    @Getter
    private final String aclType = Report.class.getName();

    @GetMapping
    @PostFilter("hasPermission(filterObject.id, @reportController.aclType, 'read')")
    public List<ReportDto> reports() {
        return reportService.allReports();

    }

    @GetMapping("{id}")
    @PreAuthorize("hasPermission(#id, @reportController.aclType, 'read')")
    public ReportDto runReport(@PathVariable Long id) {
        return reportService.report(id);
    }

    @GetMapping("{id}/run")
    @PreAuthorize("hasPermission(#id, @reportController.aclType, 'read')")
    public ReportResultDto runReport(@PathVariable Long id, HttpServletRequest request,
                                     @RequestParam(name = "fields") List<Long> fields) {
        val map = request.getParameterMap().entrySet().stream()
                .filter(stringEntry -> !stringEntry.getKey().equals("fields"))
                .collect(Collectors.toMap(stringEntry ->
                        Long.valueOf(stringEntry.getKey()), Map.Entry::getValue));
        return reportService.runReport(id, fields, map);
    }

}
