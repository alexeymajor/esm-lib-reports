package ru.avm.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avm.reports.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
