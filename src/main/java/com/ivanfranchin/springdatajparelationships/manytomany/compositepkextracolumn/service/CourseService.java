package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Course;

public interface CourseService {

    Course validateAndGetCourse(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Course course);
}
