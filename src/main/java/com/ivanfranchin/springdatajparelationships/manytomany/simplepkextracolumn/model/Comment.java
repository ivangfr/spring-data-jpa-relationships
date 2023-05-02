package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"reviewer", "article"})
@EqualsAndHashCode(exclude = {"reviewer", "article"})
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(nullable = false)
    private String text;
}
