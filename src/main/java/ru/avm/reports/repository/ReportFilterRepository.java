package ru.avm.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avm.reports.domain.ReportFilter;

import java.util.List;

public interface ReportFilterRepository extends JpaRepository<ReportFilter, Long> {
    List<ReportFilter> findAllByReportId(Long reportId);

}
