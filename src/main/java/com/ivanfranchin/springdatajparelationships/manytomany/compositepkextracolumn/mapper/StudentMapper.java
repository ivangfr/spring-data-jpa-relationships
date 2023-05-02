package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Student;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CreateStudentRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.StudentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateStudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Student toStudent(CreateStudentRequest createStudentRequest);

    StudentResponse toStudentResponse(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void updateStudentFromRequest(UpdateStudentRequest updateStudentRequest, @MappingTarget Student student);
}
