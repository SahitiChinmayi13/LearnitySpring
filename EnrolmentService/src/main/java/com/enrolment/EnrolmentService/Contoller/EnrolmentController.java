package com.enrolment.EnrolmentService.Contoller;

import com.enrolment.EnrolmentService.Model.Enrolment;
import com.enrolment.EnrolmentService.Service.EnrolmentService;
import com.enrolment.EnrolmentService.Service.EnrolmentService.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrolmentController {

    @Autowired
    private EnrolmentService enrollmentService;

    // Retrieve an enrolment by user ID
    @GetMapping("/ue/{id}")
    public ResponseEntity<Enrolment> getUsersEnroll(@PathVariable Long id) {
        try {
            Enrolment enrolment = enrollmentService.getUserEnrol(id);
            return ResponseEntity.ok(enrolment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Retrieve all enrolments
    @GetMapping
    public ResponseEntity<List<Enrolment>> getAllEnrolments() {
        List<Enrolment> enrolments = enrollmentService.getAllEnrolments();
        return ResponseEntity.ok(enrolments);
    }

    // Retrieve an enrolment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrolment> getEnrollmentById(@PathVariable Long id) {
        try {
            Enrolment enrolment = enrollmentService.getEnrollmentById(id);
            return ResponseEntity.ok(enrolment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Create a new enrolment
    @PostMapping
    public ResponseEntity<Enrolment> createEnrollment(@RequestBody Enrolment enrollment) {
        try {
            Enrolment createdEnrollment = enrollmentService.createEnrollment(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
        } catch (Exception e) {
            // Handle other potential exceptions (e.g., validation errors)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update an existing enrolment
    @PutMapping("/{id}")
    public ResponseEntity<Enrolment> updateEnrollment(@PathVariable Long id, @RequestBody Enrolment enrollment) {
        try {
            Enrolment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollment);
            if (updatedEnrollment != null) {
                return ResponseEntity.ok(updatedEnrollment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete an enrolment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
