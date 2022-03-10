package ru.avm.reports.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString

@Entity
@Table(name = "t_report_filter", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"report_id", "parameter"})
})
public class ReportFilter implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Column(name = "description")
    private String description;

    @Column(name = "clause", nullable = false)
    private String clause;

    @Column(name = "parameter", nullable = false)
    private String parameter;

    @Column(name = "parameter_type", nullable = false)
    private String parameterType;

}
