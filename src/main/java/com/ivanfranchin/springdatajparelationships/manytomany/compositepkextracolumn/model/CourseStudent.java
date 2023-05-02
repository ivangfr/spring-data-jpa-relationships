package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString(exclude = {"course", "student"})
@EqualsAndHashCode(exclude = {"course", "student"})
@Entity
@Table(name = "courses_students")
@IdClass(CourseStudentPk.class)
public class CourseStudent {

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private Instant registrationDate = Instant.now();

    private Short grade;
}
