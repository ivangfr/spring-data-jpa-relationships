package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model;

import java.io.Serializable;

public record CourseStudentPk(Long course, Long student) implements Serializable {
}
