package com.progress.ProgressService.Controller;

import com.progress.ProgressService.Model.Progress;
import com.progress.ProgressService.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

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
