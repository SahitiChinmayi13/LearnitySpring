package com.feedback.FeedbackService.Service;

import com.feedback.FeedbackService.Model.Feedback;
import com.feedback.FeedbackService.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    // Retrieve feedback by user ID
    public List<Feedback> getUserFeedback(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    // Retrieve all feedback records
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Retrieve feedback by ID, throwing an exception if not found
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found for ID: " + feedbackId));
    }

    // Create a new feedback record
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Update an existing feedback record
    public Feedback updateFeedback(Long feedbackId, Feedback feedback) {
        return feedbackRepository.findById(feedbackId)
                .map(existingFeedback -> {
                    existingFeedback.setUserId(feedback.getUserId());
                    existingFeedback.setCourseId(feedback.getCourseId());
                    existingFeedback.setDescription(feedback.getDescription());
                    existingFeedback.setRating(feedback.getRating());
                    return feedbackRepository.save(existingFeedback);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found for ID: " + feedbackId));
    }

    // Delete a feedback record by ID
    public void deleteFeedback(Long feedbackId) {
        if (feedbackRepository.existsById(feedbackId)) {
            feedbackRepository.deleteById(feedbackId);
        } else {
            throw new ResourceNotFoundException("Feedback not found for ID: " + feedbackId);
        }
    }

    // Custom exception class for handling resource not found scenarios
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
