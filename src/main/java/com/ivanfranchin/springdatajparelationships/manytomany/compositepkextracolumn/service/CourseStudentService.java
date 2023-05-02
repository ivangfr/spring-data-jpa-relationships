package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudent;

public interface CourseStudentService {

    CourseStudent validateAndGetCourseStudent(Long courseId, Long studentId);

    CourseStudent saveCourseStudent(CourseStudent courseStudent);

    void deleteCourseStudent(CourseStudent courseStudent);
}
