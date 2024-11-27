package com.user.UserService.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.UserService.dtos.CourseDto;
import com.user.UserService.dtos.UserCourseDto;

@FeignClient(name = "course-service", url = "http://localhost:8082/courses")
public interface CourseClient {
    @GetMapping("/{userId}")
    CourseDto getCourseByUserId(@PathVariable Long userId);
    
    @GetMapping("/user/{userId}/enrolled")
    List<CourseDto> getEnrolledCourses(@PathVariable Long userId);
    
    @PostMapping("/enroll")
    ResponseEntity<Void> enrollUserInCourse(@RequestBody UserCourseDto userCourseDto);
}
