package com.feedback.FeedbackService.Service;

import com.feedback.FeedbackService.Model.Course;
import com.feedback.FeedbackService.Model.Feedback;
import com.feedback.FeedbackService.Model.User;
import com.feedback.FeedbackService.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getUserFeedback(Long userid){
        return  feedbackRepository.findByUserId(userid);
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
