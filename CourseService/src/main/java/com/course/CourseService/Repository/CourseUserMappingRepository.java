package com.course.CourseService.Repository;

import com.course.CourseService.Model.CourseUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseUserMappingRepository extends JpaRepository<CourseUserMapping, Long> {
    // Find all courses that a specific user is enrolled in
    List<CourseUserMapping> findByUserId(Long userId);
    
    // Find all users enrolled in a specific course
    List<CourseUserMapping> findByCourseId(Long courseId);
}