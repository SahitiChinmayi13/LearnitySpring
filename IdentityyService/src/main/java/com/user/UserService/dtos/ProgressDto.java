package com.user.UserService.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgressDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;
    private Long userId;
    private Long courseId;
    private String status;
}
