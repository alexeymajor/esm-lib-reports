package ru.avm.reports.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

@Entity
@Table(name = "t_report")
public class Report implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Setter
    @Column(name = "entity", columnDefinition = "text")
    private String entity;
    @Setter
    @Column(name = "title", nullable = false)
    private String title;
    @Setter
    @Column(name = "text", columnDefinition = "text")
    private String text;
    @Setter
    @Column(name = "where_clause", columnDefinition = "text")
    private String whereClause;
    @Setter
    @Column(name = "position")
    private Integer position;
    @Setter
    @Column(name = "icon")
    private String icon;
    @Setter
    @Column(name = "color")
    private String color;
    @Setter
    @Builder.Default
    @Column(name = "native", nullable = false, columnDefinition = "boolean not null default false")
    private Boolean isNative = false;
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_report_field", joinColumns = {@JoinColumn(name = "report_id")})
    @OrderColumn(name = "place")
    private final List<ReportField> fields = new ArrayList<>();
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_report_field", joinColumns = {@JoinColumn(name = "report_id")})
    @OrderColumn(name = "place")
    @Where(clause = "visible = true")
    private final List<ReportField> fieldsVisible = new ArrayList<>();
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_report_filter", joinColumns = {@JoinColumn(name = "report_id")})
    @OrderColumn(name = "place")
    private final List<ReportFilter> filters = new ArrayList<>();
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_report_filter", joinColumns = {@JoinColumn(name = "report_id")})
    @OrderColumn(name = "place")
    @Where(clause = "visible = true")
    private final List<ReportFilter> filtersVisible = new ArrayList<>();
}
