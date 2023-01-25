package ru.avm.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.avm.reports.domain.Report;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.dto.ReportResultDto;
import ru.avm.reports.repository.ReportRepository;
import ru.avm.security.acl.admin.AclController;
import ru.avm.security.acl.admin.AdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder

@Transactional
@RestController
@RequestMapping("reports")
public class ReportController implements AclController {
    private final ReportService reportService;
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;
    @Getter
    private final AdminService adminService;
    @Builder.Default
    @Getter
    private final String aclType = Report.class.getName();

    @GetMapping
    public List<ReportDto> reports() {
        return reportService.allReports().stream().map(reportMapper::toDtoBrief).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_SERVCIE') || hasPermission(#id, @reportController.aclType, 'read')")
    public ReportDto report(@PathVariable Long id) {
        val report = reportRepository.findById(id).orElseThrow();
        val dto = reportMapper.toDto(report);
        System.out.println(dto);
        return dto;
    }

    @GetMapping("{id}/run")
    @PreAuthorize("hasAuthority('SCOPE_SERVCIE') || hasPermission(#id, @reportController.aclType, 'read')")
    public ReportResultDto runReport(@PathVariable Long id, HttpServletRequest request,
                                     @RequestParam(name = "fields") List<Long> fields) {
        val map = request.getParameterMap().entrySet().stream()
                .filter(stringEntry -> !stringEntry.getKey().equals("fields"))
                .collect(Collectors.toMap(stringEntry ->
                        Long.valueOf(stringEntry.getKey()), Map.Entry::getValue));
        return reportService.runReport(id, fields, map);
    }

}
