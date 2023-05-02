package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.CourseStudent;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CourseStudentResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateCourseStudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseStudentMapper {

    CourseStudentResponse toCourseStudentResponse(CourseStudent courseStudent);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    void updateCourseStudentFromRequest(UpdateCourseStudentRequest updateCourseStudentRequest,
                                        @MappingTarget CourseStudent courseStudent);
}
