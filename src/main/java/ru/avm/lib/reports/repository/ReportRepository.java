package ru.avm.lib.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avm.lib.reports.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
