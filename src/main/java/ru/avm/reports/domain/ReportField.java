package ru.avm.reports.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

@Embeddable
public class ReportField implements Serializable {
    @Column(name = "report_id", insertable = false, updatable = false)
    private Long reportId;
    @Column(name = "place", insertable = false, updatable = false)
    private Integer place;
    @Setter
    @Column(name = "id", unique = true)
    private Long id;
    @Setter
    @Column(name = "title", nullable = false)
    private String title;
    @Setter
    @Column(name = "description")
    private String description;
    @Setter
    @Column(name = "term")
    private String term;
    @Setter
    @Column(name = "report_field")
    private String field;
    @Setter
    @Column(name = "group_term")
    private String groupTerm;
    @Setter
    @Column(name = "total")
    private String total;
    @Setter
    @Column(name = "type")
    private String type;
    @Setter
    @Builder.Default
    @Column(name = "visible", nullable = false, columnDefinition = "boolean not null default true")
    private Boolean visible = true;
}
