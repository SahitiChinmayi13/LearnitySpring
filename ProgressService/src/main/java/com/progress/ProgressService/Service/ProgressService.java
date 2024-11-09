package com.progress.ProgressService.Service;

import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    // Retrieve all progress records
    public List<Progress> getAllProgressRecords() {
        return progressRepository.findAll();
    }

    // Retrieve progress by user ID, throwing an exception if not found
    public Progress getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found for user ID: " + userId));
    }

    // Retrieve progress by ID, throwing an exception if not found
    public Progress getProgressById(Long progressId) {
        return progressRepository.findById(progressId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found for ID: " + progressId));
    }

    // Create a new progress record
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    // Update an existing progress record
    public Progress updateProgress(Long progressId, Progress progress) {
        return progressRepository.findById(progressId)
                .map(existingProgress -> {
                    existingProgress.setUserId(progress.getUserId());
                    existingProgress.setCourseId(progress.getCourseId());
                    existingProgress.setStatus(progress.getStatus());
                    return progressRepository.save(existingProgress);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found for ID: " + progressId));
    }

    // Delete a progress record by ID
    public void deleteProgress(Long progressId) {
        if (progressRepository.existsById(progressId)) {
            progressRepository.deleteById(progressId);
        } else {
            throw new ResourceNotFoundException("Progress not found for ID: " + progressId);
        }
    }

    // Custom exception class for handling resource not found scenarios
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
