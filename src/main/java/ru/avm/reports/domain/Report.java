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


}
