package com.user.UserService.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.UserService.dtos.FeedbackDto;

@FeignClient(name = "feedback-service", url = "http://localhost:8085/feedback/uf")
public interface FeedbackClient {
    @GetMapping("/{userId}")
    List<FeedbackDto> getFeedbackByUserId(@PathVariable Long userId);
}