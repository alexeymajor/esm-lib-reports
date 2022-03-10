package ru.avm.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avm.reports.domain.ReportField;

public interface ReportFieldRepository extends JpaRepository<ReportField, Long> {
}
