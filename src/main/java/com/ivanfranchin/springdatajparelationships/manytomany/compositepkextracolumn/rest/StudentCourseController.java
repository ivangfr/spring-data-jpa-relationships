package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper.CourseMapper;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper.CourseStudentMapper;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper.StudentMapper;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Course;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudent;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Student;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CourseResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CourseStudentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CreateCourseRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CreateStudentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.StudentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateCourseRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateCourseStudentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateStudentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service.CourseService;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service.CourseStudentService;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentCourseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseStudentService courseStudentService;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CourseStudentMapper courseStudentMapper;

    // -------
    // Student

    @GetMapping("/students/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId) {
        Student student = studentService.validateAndGetStudent(studentId);
        return studentMapper.toStudentResponse(student);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/students")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentMapper.toStudent(createStudentRequest);
        student = studentService.saveStudent(student);
        return studentMapper.toStudentResponse(student);
    }

    @PutMapping("/students/{studentId}")
    public StudentResponse updateStudent(@PathVariable Long studentId,
                                         @Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        Student student = studentService.validateAndGetStudent(studentId);
        studentMapper.updateStudentFromRequest(updateStudentRequest, student);
        student = studentService.saveStudent(student);
        return studentMapper.toStudentResponse(student);
    }

    @DeleteMapping("/students/{studentId}")
    public StudentResponse deleteStudent(@PathVariable Long studentId) {
        Student student = studentService.validateAndGetStudent(studentId);
        studentService.deleteStudent(student);
        return studentMapper.toStudentResponse(student);
    }

    // ------
    // Course

    @GetMapping("/courses/{courseId}")
    public CourseResponse getCourse(@PathVariable Long courseId) {
        Course course = courseService.validateAndGetCourse(courseId);
        return courseMapper.toCourseResponse(course);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses")
    public CourseResponse createCourse(@Valid @RequestBody CreateCourseRequest createCourseRequest) {
        Course course = courseMapper.toCourse(createCourseRequest);
        course = courseService.saveCourse(course);
        return courseMapper.toCourseResponse(course);
    }

    @PutMapping("/courses/{courseId}")
    public CourseResponse updateCourse(@PathVariable Long courseId,
                                       @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
        Course course = courseService.validateAndGetCourse(courseId);
        courseMapper.updateCourseFromRequest(updateCourseRequest, course);
        course = courseService.saveCourse(course);
        return courseMapper.toCourseResponse(course);
    }

    @DeleteMapping("/courses/{courseId}")
    public CourseResponse deleteCourse(@PathVariable Long courseId) {
        Course course = courseService.validateAndGetCourse(courseId);
        courseService.deleteCourse(course);
        return courseMapper.toCourseResponse(course);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses/{courseId}/students/{studentId}")
    public CourseStudentResponse enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseService.validateAndGetCourse(courseId);
        Student student = studentService.validateAndGetStudent(studentId);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent = courseStudentService.saveCourseStudent(courseStudent);

        return courseStudentMapper.toCourseStudentResponse(courseStudent);
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public CourseStudentResponse unregisterStudentOfCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        CourseStudent courseStudent = courseStudentService.validateAndGetCourseStudent(courseId, studentId);
        courseStudentService.deleteCourseStudent(courseStudent);
        return courseStudentMapper.toCourseStudentResponse(courseStudent);
    }

    @PutMapping("/courses/{courseId}/students/{studentId}")
    public CourseStudentResponse updateStudentDataInCourse(@PathVariable Long courseId, @PathVariable Long studentId,
                                                           @Valid @RequestBody UpdateCourseStudentRequest updateCourseStudentRequest) {
        CourseStudent courseStudent = courseStudentService.validateAndGetCourseStudent(courseId, studentId);
        courseStudentMapper.updateCourseStudentFromRequest(updateCourseStudentRequest, courseStudent);
        courseStudentService.saveCourseStudent(courseStudent);
        return courseStudentMapper.toCourseStudentResponse(courseStudent);
    }
}
