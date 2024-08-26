package com.enrolment.EnrolmentService.Contoller;

import com.enrolment.EnrolmentService.Model.Enrolment;
import com.enrolment.EnrolmentService.Repository.EnrolmentRepository;
import com.enrolment.EnrolmentService.Service.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrolmentController {
    @Autowired
    private EnrolmentService enrollmentService;

    @GetMapping("/{id}")
    public Enrolment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping
    public Enrolment createEnrollment(@RequestBody Enrolment enrollment) {
        return enrollmentService.createEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public Enrolment updateEnrollment(@PathVariable Long id, @RequestBody Enrolment enrollment) {
        return enrollmentService.updateEnrollment(id, enrollment);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}
