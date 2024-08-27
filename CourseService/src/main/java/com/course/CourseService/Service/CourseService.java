package com.course.CourseService.Service;

import com.course.CourseService.Model.Course;
import com.course.CourseService.Model.Enrolment;
import com.course.CourseService.Model.Feedback;
import com.course.CourseService.Model.Progress;
import com.course.CourseService.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String ENROLMENT_SERVICE_URL = "http://localhost:8082/enrolments";
    private final String PROGRESS_SERVICE_URL = "http://localhost:8083/progress";
    private final String FEEDBACK_SERVICE_URL = "http://localhost:8084/feedback";

    public Enrolment getEnrolmentsByCourseId(Long courseId) {
        return restTemplate.getForObject(ENROLMENT_SERVICE_URL + "/course/" + courseId, Enrolment.class);
    }

    public Progress getProgressByCourseId(Long courseId) {
        return restTemplate.getForObject(PROGRESS_SERVICE_URL + "/course/" + courseId, Progress.class);
    }

    public Feedback getFeedbackByCourseId(Long courseId) {
        return restTemplate.getForObject(FEEDBACK_SERVICE_URL + "/course/" + courseId, Feedback.class);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        if (courseRepository.existsById(id)) {
            Course existingCourse = courseRepository.findById(id).get();
            existingCourse.setTitle(course.getTitle());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setDuration(course.getDuration());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
