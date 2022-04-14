package ru.avm.reports.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name = "description")
    private String description;

    @Column(name = "clause", nullable = false)
    private String clause;

    @Column(name = "parameter_type", nullable = false)
    private String type;

    @Type(type = "json")
    @Column(name = "expression", columnDefinition = "json")
    private ReportFilterExpressionDto expression;

}
