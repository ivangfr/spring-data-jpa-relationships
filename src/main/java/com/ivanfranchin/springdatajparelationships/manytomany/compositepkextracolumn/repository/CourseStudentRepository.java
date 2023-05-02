package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudent;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudentPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends CrudRepository<CourseStudent, CourseStudentPk> {
}
