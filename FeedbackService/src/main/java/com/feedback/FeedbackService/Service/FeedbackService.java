package com.feedback.FeedbackService.Service;

import com.feedback.FeedbackService.Model.Course;
import com.feedback.FeedbackService.Model.Feedback;
import com.feedback.FeedbackService.Model.User;
import com.feedback.FeedbackService.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8081/users";
    private final String COURSE_SERVICE_URL = "http://localhost:8082/courses";

    // Example method to get user details for feedback
    public User getUserByFeedback(Long feedbackId) {
        Feedback feedback = restTemplate.getForObject("/feedback/" + feedbackId, Feedback.class);
        return restTemplate.getForObject(USER_SERVICE_URL + "/" + feedback.getUserId(), User.class);
    }

    // Example method to get course details for feedback
    public Course getCourseByFeedback(Long feedbackId) {
        Feedback feedback = restTemplate.getForObject("/feedback/" + feedbackId, Feedback.class);
        return restTemplate.getForObject(COURSE_SERVICE_URL + "/" + feedback.getCourseId(), Course.class);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long feedbackId, Feedback feedback) {
        // Check if the feedback record exists
        if (feedbackRepository.existsById(feedbackId)) {
            // Retrieve the existing feedback
            Feedback existingFeedback = feedbackRepository.findById(feedbackId).get();
            // Update fields
            existingFeedback.setUserId(feedback.getUserId());
            existingFeedback.setCourseId(feedback.getCourseId());
            existingFeedback.setDescription(feedback.getDescription());
            existingFeedback.setRating(feedback.getRating());
            // Save and return updated feedback
            return feedbackRepository.save(existingFeedback);
        }
        return null; // or throw an exception if not found
    }

    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
