package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;

public record CourseStudentResponse(Course course, Student student, Date registrationDate, Short grade) {

    public record Course(Long id, String name) {
    }

    public record Student(Long id, String name) {
    }
}
