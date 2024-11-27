package com.user.UserService.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
 private long courseId;
 private String title;
 private String description;
 private int duration;
 private int module;
}

