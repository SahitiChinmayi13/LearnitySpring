package com.user.UserService.Service;


import com.user.UserService.Model.*;
import com.user.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
        try {
            // Fetch enrolment information
            Enrolment enrolment = restTemplate.getForObject(ENROLMENT_SERVICE_URL + "/" + userId, Enrolment.class);
            if (enrolment == null) {
                throw new ResourceNotFoundException("Enrolment not found for user id: " + userId);
            }

            // Fetch course details
            Course course = restTemplate.getForObject(COURSE_SERVICE_URL + "/" + enrolment.getCourseId(), Course.class);
            if (course == null) {
                throw new ResourceNotFoundException("Course not found for course id: " + enrolment.getCourseId());
            }

            return course;
        } catch (RestClientException e) {
            // Handle errors related to external service calls
            throw new ServiceUnavailableException("Course service is currently unavailable.", e);
        }
    }



    public Progress getProgressByUserId(Long userId) {
        try {
            Progress progress = restTemplate.getForObject(PROGRESS_SERVICE_URL + "/" + userId, Progress.class);
            if (progress == null) {
                throw new ResourceNotFoundException("Progress not found for user id: " + userId);
            }
            return progress;
        } catch (RestClientException e) {
            // Handle errors related to external service calls
            throw new ServiceUnavailableException("Progress service is currently unavailable.", e);
        }
    }

    public List<Feedback> getFeedbackByUserId(Long userId) {
        try {
            ResponseEntity<List> response = restTemplate.getForEntity(FEEDBACK_SERVICE_URL + "/" + userId, List.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new ResourceNotFoundException("Feedback not found for user id: " + userId);
            }
        } catch (RestClientException e) {
            // Handle errors related to external service calls
            throw new ServiceUnavailableException("Feedback service is currently unavailable.", e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw  new ResourceNotFoundException("User not found for username: " + username);
        }
    }

    public Role getRole1(String username){
        return userRepository.findUserRoleByUsername(username);
    }

    public User createUser(User user) {
        // Hash the password before saving it (implement hashing here)
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found for id: " + id);
        }
        userRepository.deleteById(id);
    }


    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}