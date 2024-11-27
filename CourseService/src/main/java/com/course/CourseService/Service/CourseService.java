package com.course.CourseService.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.CourseService.Model.Course;
import com.course.CourseService.Model.CourseUserMapping;
import com.course.CourseService.Model.UserCourseDto;
import com.course.CourseService.Repository.CourseRepository;
import com.course.CourseService.Repository.CourseUserMappingRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private CourseUserMappingRepository mappingRepository;


    // Retrieve a course by its ID, throwing an exception if not found
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id: " + id));
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course
    public Course updateCourse(Long id, Course course) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setTitle(course.getTitle());
                    existingCourse.setDescription(course.getDescription());
                    existingCourse.setDuration(course.getDuration());
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for id: " + id));
    }

    // Delete a course by its ID
    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Course not found for id: " + id);
        }
    }
    
    public List<Course> getEnrolledCourses(Long userId) {
        List<CourseUserMapping> mappings = mappingRepository.findByUserId(userId);
        return mappings.stream()
            .map(mapping -> getCourseById(mapping.getCourseId()))
            .collect(Collectors.toList());
    }

    public void enrollUserInCourse(UserCourseDto userCourseDto) {
        CourseUserMapping mapping = new CourseUserMapping();
        mapping.setUserId(userCourseDto.getUserId());
        mapping.setCourseId(userCourseDto.getCourseId());
        mappingRepository.save(mapping);
    }

    // Custom exception class for handling resource not found scenarios
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
