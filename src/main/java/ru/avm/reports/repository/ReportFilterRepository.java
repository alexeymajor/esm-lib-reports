package ru.avm.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avm.reports.domain.ReportFilter;

public interface ReportFilterRepository extends JpaRepository<ReportFilter, Long> {

}
