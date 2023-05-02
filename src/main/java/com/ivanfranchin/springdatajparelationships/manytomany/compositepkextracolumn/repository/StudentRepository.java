package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
