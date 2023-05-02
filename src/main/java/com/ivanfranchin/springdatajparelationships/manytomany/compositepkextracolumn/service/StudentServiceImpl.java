package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.exception.StudentNotFoundException;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Student;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student validateAndGetStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
