package com.course.CourseService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.CourseService.Model.Course;
import com.course.CourseService.Model.UserCourseDto;
import com.course.CourseService.Service.CourseService;
import com.course.CourseService.Service.CourseService.ResourceNotFoundException;

@RestController
@RequestMapping("/courses")
//@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Retrieve a course by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Retrieve all courses
    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Create a new course
    @PostMapping("/addCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    	System.out.println("Received course data: " + course);
        try {
            Course createdCourse = courseService.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (Exception e) {
            // Handle other potential exceptions (e.g., validation errors)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update an existing course
    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(id, course);
            if (updatedCourse != null) {
                return ResponseEntity.ok(updatedCourse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a course by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/user/{userId}/enrolled")
    public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable Long userId) {
        try {
            List<Course> courses = courseService.getEnrolledCourses(userId);
            return ResponseEntity.ok(courses);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<Void> enrollUserInCourse(@RequestBody UserCourseDto userCourseDto) {
        try {
            courseService.enrollUserInCourse(userCourseDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
