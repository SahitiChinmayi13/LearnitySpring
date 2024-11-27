package com.user.UserService.Controller;

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

import com.user.UserService.Entity.Role;
import com.user.UserService.Entity.User;
import com.user.UserService.Service.UserService;
import com.user.UserService.Service.UserService.ResourceNotFoundException;
import com.user.UserService.Service.UserService.ServiceUnavailableException;
import com.user.UserService.dtos.CourseDto;
import com.user.UserService.dtos.FeedbackDto;
import com.user.UserService.dtos.ProgressDto;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/u/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            System.out.println("0" + id);
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/u/coursedetail/{id}")
    public ResponseEntity<CourseDto> getUserCourseDetail(@PathVariable Long id) {
        try {
            CourseDto course = userService.getCourseByUserId(id);
            return ResponseEntity.ok(course);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/u/userprogress/{id}")
    public ResponseEntity<ProgressDto> getUserProgress(@PathVariable Long id) {
        try {
            ProgressDto progress = userService.getProgressByUserId(id);
            return ResponseEntity.ok(progress);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/userfeedback/{id}")
    public ResponseEntity<List<FeedbackDto>> getUserFeedback(@PathVariable Long id) {
        try {
            List<FeedbackDto> feedback = userService.getFeedbackByUserId(id);
            return ResponseEntity.ok(feedback);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/u/role/{username}")
    public ResponseEntity<Role> getRole(@PathVariable String username){
        System.out.println("vishal" + userService.getRole1(username));
        return ResponseEntity.ok().body(userService.getRole1(username));
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<List<CourseDto>> getUserEnrolledCourses(@PathVariable Long userId) {
        try {
            List<CourseDto> courses = userService.getEnrolledCourses(userId);
            return ResponseEntity.ok(courses);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @PostMapping("/u/{userId}/courses/{courseId}")
    public ResponseEntity<Void> enrollUserInCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        try {
            userService.enrollUserInCourse(userId, courseId);
            return ResponseEntity.ok().build();
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}

