package ru.avm.reports.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.avm.reports.dto.ReportFilterExpressionDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class),
})

@Entity
@Table(name = "t_report_filter")
public class ReportFilter implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "report_id", nullable = false)
    private Long reportId;

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
    private ReportFilterExpressionDto expression;

    @Setter
    @Builder.Default
    @Column(name = "visible", nullable = false, columnDefinition = "boolean not null default true")
    private Boolean visible = true;

    @Setter
    @Column(name = "place")
    private Integer place;

}
