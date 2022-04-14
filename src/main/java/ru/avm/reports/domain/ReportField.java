package ru.avm.reports.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString

@Entity
@Table(name = "t_report_field")
public class ReportField implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Column(name = "title", nullable = false)
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "term")
    private String term;

    @Column(name = "report_field")
    private String field;

    @Column(name = "group_term")
    private String groupTerm;

    @Column(name = "total")
    private String total;

    @Column(name = "type")
    private String type;

}
