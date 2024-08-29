package com.feedback.FeedbackService.Controller;

import com.feedback.FeedbackService.Model.Feedback;
import com.feedback.FeedbackService.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable Long feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping("/uf/{id}")
    public List<Feedback> getUsersFeedback(@PathVariable  Long id){
        return feedbackService.getUserFeedback(id);
    }

    @GetMapping
    public List<Feedback> getALlFeedback(){
        return feedbackService.getAllFeedback();
    }

    @PutMapping("/{feedbackId}")
    public Feedback updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(feedbackId, feedback);
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }
}
