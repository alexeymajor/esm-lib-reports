package ru.avm.lib.reports.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter


@Embeddable
public class ReportFilter implements Serializable {
    @Column(name = "report_id", insertable = false, updatable = false)
    private Long reportId;
    @Column(name = "place", insertable = false, updatable = false)
    private Integer place;
    @Setter
    @Column(name = "id", unique = true)
    private Long id;
    @Setter
    @Column(name = "parameter")
    private String parameter;
    @Setter
    @Column(name = "required")
    private String required;
    @Setter
    @Column(name = "description")
    private String description;
    @Setter
    @Column(name = "clause", nullable = false)
    private String clause;
    @Setter
    @Column(name = "parameter_type", nullable = false)
    private String type;
    @Setter
    @Type(JsonType.class)
    @Column(name = "expression", columnDefinition = "json")
    private Object expression;
    @Setter
    @Builder.Default
    @Column(name = "visible", nullable = false, columnDefinition = "boolean not null default true")
    private Boolean visible = true;
}
