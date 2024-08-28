package com.user.UserService.Controller;

import com.user.UserService.Model.Course;
import com.user.UserService.Model.Feedback;
import com.user.UserService.Model.Progress;
import com.user.UserService.Model.User;
import com.user.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/coursedetail/{id}")
    public Course getUserCourseDetail(@PathVariable Long id){
        return userService.getCourseDetailsForUser(id);
    }

    @GetMapping("/userprogress/{id}")
    public Progress getUserProgress(@PathVariable Long id){
        return userService.getProgressByUserId(id);
    }

    @GetMapping("/userfeedback/{id}")
    public Feedback getUserFeedback(@PathVariable Long id){
        return userService.getFeedbackByUserId(id);
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}