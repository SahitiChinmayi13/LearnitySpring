package com.progress.ProgressService.Controller;

import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Service.ProgressService;
import com.progress.ProgressService.Service.ProgressService.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Retrieve progress by user ID
    @GetMapping("/up/{id}")
    public ResponseEntity<Progress> getUserProgress(@PathVariable Long id) {
        try {
            Progress progress = progressService.getUserProgress(id);
            return ResponseEntity.ok(progress);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Retrieve all progress records
    @GetMapping
    public ResponseEntity<List<Progress>> getAllProgressRecords() {
        List<Progress> progressRecords = progressService.getAllProgressRecords();
        return ResponseEntity.ok(progressRecords);
    }

    // Retrieve progress by ID
    @GetMapping("/{progressId}")
    public ResponseEntity<Progress> getProgressById(@PathVariable Long progressId) {
        try {
            Progress progress = progressService.getProgressById(progressId);
            return ResponseEntity.ok(progress);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Create a new progress record
    @PostMapping
    public ResponseEntity<Progress> createProgress(@RequestBody Progress progress) {
        try {
            Progress createdProgress = progressService.createProgress(progress);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProgress);
        } catch (Exception e) {
            // Handle other potential exceptions, e.g., validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update an existing progress record
    @PutMapping("/{progressId}")
    public ResponseEntity<Progress> updateProgress(@PathVariable Long progressId, @RequestBody Progress progress) {
        try {
            Progress updatedProgress = progressService.updateProgress(progressId, progress);
            if (updatedProgress != null) {
                return ResponseEntity.ok(updatedProgress);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a progress record
    @DeleteMapping("/{progressId}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long progressId) {
        try {
            progressService.deleteProgress(progressId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
