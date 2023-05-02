package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;
import java.util.List;

public record StudentResponse(Long id, String name, List<CourseStudent> courses) {

    public record CourseStudent(Course course, Date registrationDate, Short grade) {

        public record Course(Long id, String name) {
        }
    }
}
