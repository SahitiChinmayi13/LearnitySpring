package com.progress.ProgressService.Service;

import com.progress.ProgressService.Model.Course;
import com.progress.ProgressService.Model.Enrolment;
import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Model.User;
import com.progress.ProgressService.Repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8081/users";
    private final String COURSE_SERVICE_URL = "http://localhost:8082/courses";
    private final String ENROLMENT_SERVICE_URL = "http://localhost:8082/enrolments";

    // Get user details by progress
    public User getUserByProgress(Long progressId) {
        Progress progress = restTemplate.getForObject("/progress/" + progressId, Progress.class);
        return restTemplate.getForObject(USER_SERVICE_URL + "/" + progress.getUserId(), User.class);
    }

    // Get course details by progress
    public Course getCourseByProgress(Long progressId) {
        Progress progress = restTemplate.getForObject("/progress/" + progressId, Progress.class);
        return restTemplate.getForObject(COURSE_SERVICE_URL + "/" + progress.getCourseId(), Course.class);
    }

    // Get enrolment details by progress
    public Enrolment getEnrolmentByProgress(Long progressId) {
        Progress progress = restTemplate.getForObject("/progress/" + progressId, Progress.class);
        return restTemplate.getForObject(ENROLMENT_SERVICE_URL + "/course/" + progress.getCourseId() + "/user/" + progress.getUserId(), Enrolment.class);
    }

    public List<Progress> getAllProgressRecords() {
        return progressRepository.findAll();
    }

    public Progress getProgressById(Long progressId) {
        return progressRepository.findById(progressId).orElse(null);
    }

    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    public Progress updateProgress(Long progressId, Progress progress) {
        // Check if the progress record exists
        if (progressRepository.existsById(progressId)) {
            // Retrieve the existing progress
            Progress existingProgress = progressRepository.findById(progressId).get();
            // Update fields
            existingProgress.setUserId(progress.getUserId());
            existingProgress.setCourseId(progress.getCourseId());
            existingProgress.setStatus(progress.getStatus());
            // Save and return updated progress
            return progressRepository.save(existingProgress);
        }
        return null; // or throw an exception if not found
    }

    public void deleteProgress(Long progressId) {
        progressRepository.deleteById(progressId);
    }
}