package com.enrolment.EnrolmentService.Service;

import com.enrolment.EnrolmentService.Model.Enrolment;
import com.enrolment.EnrolmentService.Repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolmentService {

    @Autowired
    private EnrolmentRepository enrollmentRepository;

    // Retrieve an enrolment by user ID, throwing an exception if not found
    public Enrolment getUserEnrol(Long userId) {
        return enrollmentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrolment not found for user ID: " + userId));
    }

    // Retrieve all enrolments
    public List<Enrolment> getAllEnrolments() {
        return enrollmentRepository.findAll();
    }

    // Retrieve an enrolment by ID, throwing an exception if not found
    public Enrolment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrolment not found for ID: " + id));
    }

    // Create a new enrolment
    public Enrolment createEnrollment(Enrolment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Update an existing enrolment
    public Enrolment updateEnrollment(Long id, Enrolment enrollment) {
        return enrollmentRepository.findById(id)
                .map(existingEnrollment -> {
                    existingEnrollment.setUserId(enrollment.getUserId());
                    existingEnrollment.setCourseId(enrollment.getCourseId());
                    existingEnrollment.setStartDate(enrollment.getStartDate());
                    return enrollmentRepository.save(existingEnrollment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Enrolment not found for ID: " + id));
    }

    // Delete an enrolment by ID
    public void deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Enrolment not found for ID: " + id);
        }
    }

    // Custom exception class for handling resource not found scenarios
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
