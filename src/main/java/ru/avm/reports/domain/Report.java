package ru.avm.reports.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

@Entity
@Table(name = "t_report")
public class Report implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "where_clause")
    private String whereClause;

    @Builder.Default
    @Column(name = "native", nullable = false, columnDefinition = "boolean not null default false")
    private Boolean isNative = false;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @OrderBy("place")
    private final List<ReportField> fields = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @OrderBy("place")
    private final List<ReportFilter> filters = new ArrayList<>();

}
