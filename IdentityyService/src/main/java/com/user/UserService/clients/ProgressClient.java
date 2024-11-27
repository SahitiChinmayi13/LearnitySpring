package com.user.UserService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.UserService.dtos.ProgressDto;

@FeignClient(name = "progress-service", url = "http://localhost:8084/progress/up")
public interface ProgressClient {
    @GetMapping("/{userId}")
    ProgressDto getProgressByUserId(@PathVariable Long userId);
}
