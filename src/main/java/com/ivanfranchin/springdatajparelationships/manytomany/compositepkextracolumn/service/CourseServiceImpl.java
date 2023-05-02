package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.exception.CourseNotFoundException;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Course;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course validateAndGetCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }
}
