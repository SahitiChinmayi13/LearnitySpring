package com.course.CourseService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseDto {
    private Long id;
    private Long userId;
    private Long courseId;
}