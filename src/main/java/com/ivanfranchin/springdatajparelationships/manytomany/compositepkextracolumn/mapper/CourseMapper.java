package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.mapper;

import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.model.Course;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CourseResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.CreateCourseRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto.UpdateCourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course toCourse(CreateCourseRequest createCourseRequest);

    CourseResponse toCourseResponse(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateCourseFromRequest(UpdateCourseRequest updateCourseRequest, @MappingTarget Course course);
}
