package com.enrolment.EnrolmentService.Repository;

import com.enrolment.EnrolmentService.Model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {
}