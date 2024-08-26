package com.progress.ProgressService.Service;

import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

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