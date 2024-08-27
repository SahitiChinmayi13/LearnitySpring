package com.enrolment.EnrolmentService.Service;

import com.enrolment.EnrolmentService.Model.Course;
import com.enrolment.EnrolmentService.Model.Enrolment;
import com.enrolment.EnrolmentService.Model.Progress;
import com.enrolment.EnrolmentService.Model.User;
import com.enrolment.EnrolmentService.Repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EnrolmentService {
    @Autowired
    private EnrolmentRepository enrollmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8081/users";
    private final String COURSE_SERVICE_URL = "http://localhost:8082/courses";
    private final String PROGRESS_SERVICE_URL = "http://localhost:8083/progress";

    // Get user details by enrolment
    public User getUserByEnrolment(Long enrolId) {
        Enrolment enrolment = restTemplate.getForObject("/enrolments/" + enrolId, Enrolment.class);
        return restTemplate.getForObject(USER_SERVICE_URL + "/" + enrolment.getUserId(), User.class);
    }

    // Get course details by enrolment
    public Course getCourseByEnrolment(Long enrolId) {
        Enrolment enrolment = restTemplate.getForObject("/enrolments/" + enrolId, Enrolment.class);
        return restTemplate.getForObject(COURSE_SERVICE_URL + "/" + enrolment.getCourseId(), Course.class);
    }

    // Get progress by enrolment
    public Progress getProgressByEnrolment(Long enrolId) {
        Enrolment enrolment = restTemplate.getForObject("/enrolments/" + enrolId, Enrolment.class);
        return restTemplate.getForObject(PROGRESS_SERVICE_URL + "/course/" + enrolment.getCourseId() + "/user/" + enrolment.getUserId(), Progress.class);
    }

    public List<Enrolment> getAllEnrolments() {
        return enrollmentRepository.findAll();
    }



    public Enrolment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public Enrolment createEnrollment(Enrolment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrolment updateEnrollment(Long id, Enrolment enrollment) {
        // Check if the enrollment exists
        if (enrollmentRepository.existsById(id)) {
            // Retrieve the existing enrollment
            Enrolment existingEnrollment = enrollmentRepository.findById(id).get();
            // Update fields
            existingEnrollment.setUserId(enrollment.getUserId());
            existingEnrollment.setCourseId(enrollment.getCourseId());
            existingEnrollment.setStartDate(enrollment.getStartDate());
            // Save and return updated enrollment
            return enrollmentRepository.save(existingEnrollment);
        }
        return null; // or throw an exception if not found
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}