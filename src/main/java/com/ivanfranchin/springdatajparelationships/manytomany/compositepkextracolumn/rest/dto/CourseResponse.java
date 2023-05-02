package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import java.util.Date;
import java.util.List;

public record CourseResponse(Long id, String name, List<CourseStudent> students) {

    public record CourseStudent(Student student, Date registrationDate, Short grade) {

        public record Student(Long id, String name) {
        }
    }
}
