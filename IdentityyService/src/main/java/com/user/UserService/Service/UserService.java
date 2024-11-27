package com.user.UserService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.UserService.Entity.Role;
import com.user.UserService.Entity.User;
import com.user.UserService.Repository.UserRepository;
import com.user.UserService.clients.CourseClient;
import com.user.UserService.clients.FeedbackClient;
import com.user.UserService.clients.ProgressClient;
import com.user.UserService.dtos.CourseDto;
import com.user.UserService.dtos.FeedbackDto;
import com.user.UserService.dtos.ProgressDto;
import com.user.UserService.dtos.UserCourseDto;

import feign.FeignException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseClient courseClient;
    
    @Autowired
    private ProgressClient progressClient;
    
    @Autowired
    private FeedbackClient feedbackClient;

    public CourseDto getCourseByUserId(Long userId) {
        try {
            return courseClient.getCourseByUserId(userId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Course not found for user id: " + userId);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("Course service is currently unavailable.", e);
        }
    }

    public ProgressDto getProgressByUserId(Long userId) {
        try {
            return progressClient.getProgressByUserId(userId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Progress not found for user id: " + userId);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("Progress service is currently unavailable.", e);
        }
    }

    public List<FeedbackDto> getFeedbackByUserId(Long userId) {
        try {
            return feedbackClient.getFeedbackByUserId(userId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Feedback not found for user id: " + userId);
        } catch (FeignException e) {
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
    public List<CourseDto> getEnrolledCourses(Long userId) {
        try {
            return courseClient.getEnrolledCourses(userId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("No courses found for user id: " + userId);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("Course service is currently unavailable.", e);
        }
    }

    public void enrollUserInCourse(Long userId, Long courseId) {
        try {
            UserCourseDto userCourseDto = new UserCourseDto();
            userCourseDto.setUserId(userId);
            userCourseDto.setCourseId(courseId);
            courseClient.enrollUserInCourse(userCourseDto);
        } catch (FeignException e) {
            throw new ServiceUnavailableException("Failed to enroll user in course", e);
        }
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