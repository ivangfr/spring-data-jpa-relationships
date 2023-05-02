package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.exception.CourseStudentNotFoundException;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudent;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudentPk;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository.CourseStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;

    @Override
    public CourseStudent validateAndGetCourseStudent(Long courseId, Long studentId) {
        CourseStudentPk courseStudentPk = new CourseStudentPk(courseId, studentId);
        return courseStudentRepository.findById(courseStudentPk)
                .orElseThrow(() -> new CourseStudentNotFoundException(courseStudentPk));
    }

    @Override
    public CourseStudent saveCourseStudent(CourseStudent courseStudent) {
        return courseStudentRepository.save(courseStudent);
    }

    @Override
    public void deleteCourseStudent(CourseStudent courseStudent) {
        courseStudentRepository.delete(courseStudent);
    }
}
