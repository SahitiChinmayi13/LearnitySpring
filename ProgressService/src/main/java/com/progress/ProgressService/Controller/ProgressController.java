package com.progress.ProgressService.Controller;

import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    @GetMapping("/up/{id}")
    public Progress getUserProgress(@PathVariable Long id){
        return progressService.getUserProgress(id);
    }

    @GetMapping
    public List<Progress> getAllProgressRecords() {
        return progressService.getAllProgressRecords();
    }

    @GetMapping("/{progressId}")
    public Progress getProgressById(@PathVariable Long progressId) {
        return progressService.getProgressById(progressId);
    }

    @PostMapping
    public Progress createProgress(@RequestBody Progress progress) {
        return progressService.createProgress(progress);
    }

    @PutMapping("/{progressId}")
    public Progress updateProgress(@PathVariable Long progressId, @RequestBody Progress progress) {
        return progressService.updateProgress(progressId, progress);
    }

    @DeleteMapping("/{progressId}")
    public void deleteProgress(@PathVariable Long progressId) {
        progressService.deleteProgress(progressId);
    }
}
