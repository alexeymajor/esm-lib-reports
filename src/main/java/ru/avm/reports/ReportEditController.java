package ru.avm.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.avm.profile.ProfileController;
import ru.avm.profile.ProfileService;
import ru.avm.reports.domain.Report;
import ru.avm.reports.dto.ReportDto;
import ru.avm.reports.repository.ReportRepository;
import ru.avm.security.acl.admin.AclController;
import ru.avm.security.acl.admin.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder

@Transactional
@RestController
@RequestMapping("reports/edit")
public class ReportEditController implements AclController, ProfileController {
    private final ReportService reportService;
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;
    @Getter
    private final AdminService adminService;
    @Getter
    private final ProfileService profileService;
    @Getter
    private final String alias = "reports";
    @Builder.Default
    @Getter
    private final String aclType = Report.class.getName();

    @GetMapping
    @PostFilter("hasPermission(filterObject.id, @reportController.aclType, @adminService.specialPermission)")
    public List<ReportDto> reports() {
        return reportService.allReports().stream().map(reportMapper::toDtoListFull).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasPermission(#id, @reportController.aclType, @adminService.specialPermission)")
    public ReportDto reportForEdit(@PathVariable Long id) {
        return reportRepository.findById(id).map(reportMapper::toDtoFull).orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasPermission(0, @reportController.aclType, @adminService.specialPermission)")
    public Long create(@RequestBody ReportDto dto) {
        val domain = reportMapper.toDomain(dto);
        reportRepository.save(domain);
        return domain.getId();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasPermission(#id, @reportController.aclType, @adminService.specialPermission)")
    public void update(@PathVariable Long id, @RequestBody ReportDto dto) {
        val domain = reportRepository.findById(id).orElseThrow();
        reportMapper.updateDomain(domain, dto);
        reportRepository.save(domain);
    }

}
