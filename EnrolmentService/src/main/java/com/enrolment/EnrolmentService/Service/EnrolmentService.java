package com.enrolment.EnrolmentService.Service;

import com.enrolment.EnrolmentService.Model.Enrolment;
import com.enrolment.EnrolmentService.Repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolmentService {
    @Autowired
    private EnrolmentRepository enrollmentRepository;

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