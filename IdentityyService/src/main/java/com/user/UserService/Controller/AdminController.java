package com.user.UserService.Controller;

import com.user.UserService.Entity.Role;
import com.user.UserService.Entity.User;
import com.user.UserService.Service.UserService;
import com.user.UserService.dtos.CourseDto;
import com.user.UserService.dtos.ProgressDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserService.ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/u/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            System.out.println("0" + id);
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserService.ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/u/coursedetail/{id}")
    public ResponseEntity<CourseDto> getUserCourseDtoDetail(@PathVariable Long id) {
        try {
            CourseDto course = userService.getCourseByUserId(id);
            return ResponseEntity.ok(course);
        } catch (UserService.ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UserService.ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    @GetMapping("/u/userprogress/{id}")
    public ResponseEntity<ProgressDto> getUserProgress(@PathVariable Long id) {
        try {
            ProgressDto progress = userService.getProgressByUserId(id);
            return ResponseEntity.ok(progress);
        } catch (UserService.ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UserService.ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }


    @GetMapping("/u/role/{username}")
    public ResponseEntity<Role> getRole(@PathVariable String username){
        System.out.println("vishal" + userService.getRole1(username));
        return ResponseEntity.ok().body(userService.getRole1(username));
    }


}

