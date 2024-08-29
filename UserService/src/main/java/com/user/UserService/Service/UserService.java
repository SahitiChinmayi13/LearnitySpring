package com.user.UserService.Service;


import com.user.UserService.Model.*;
import com.user.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String COURSE_SERVICE_URL = "http://localhost:8082/courses";
    private final String ENROLMENT_SERVICE_URL = "http://localhost:8083/enrollments/ue";
    private final String PROGRESS_SERVICE_URL = "http://localhost:8084/progress/up";
    private final String FEEDBACK_SERVICE_URL = "http://localhost:8085/feedback/uf";

    public Course getCourseDetailsForUser(Long userId) {
        // Assuming enrolment service provides courseId for the user
        Enrolment enrolment = restTemplate.getForObject(ENROLMENT_SERVICE_URL + "/" + userId, Enrolment.class);
        return restTemplate.getForObject(COURSE_SERVICE_URL + "/" + enrolment.getCourseId(), Course.class);
    }


    public Progress getProgressByUserId(Long userId) {
        return restTemplate.getForObject(PROGRESS_SERVICE_URL + "/" + userId, Progress.class);
    }

    public List<Feedback> getFeedbackByUserId(Long userId) {
        return restTemplate.getForObject(FEEDBACK_SERVICE_URL + "/" + userId, List.class);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        // Hash the password before saving it (implement hashing here)
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        // Hash the password if it is being updated (implement hashing here)
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}