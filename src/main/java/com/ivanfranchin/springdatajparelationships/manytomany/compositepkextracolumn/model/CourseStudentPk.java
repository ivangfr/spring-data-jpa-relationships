package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentPk implements Serializable {

    private Long course;
    private Long student;
}
