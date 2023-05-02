package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
