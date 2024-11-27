package com.user.UserService.Repository;

import com.user.UserService.Entity.UserCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseMappingRepository extends JpaRepository<UserCourseMapping, Long> {
    // Find all courses that a specific user is enrolled in
    List<UserCourseMapping> findByUserId(Long userId);
    
    // Find all users enrolled in a specific course
    List<UserCourseMapping> findByCourseId(Long courseId);
}