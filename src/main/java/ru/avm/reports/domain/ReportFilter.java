package ru.avm.reports.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class),
})

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
    @Type(type = "json")
    @Column(name = "expression", columnDefinition = "json")
    private Object expression;
    @Setter
    @Builder.Default
    @Column(name = "visible", nullable = false, columnDefinition = "boolean not null default true")
    private Boolean visible = true;
}
