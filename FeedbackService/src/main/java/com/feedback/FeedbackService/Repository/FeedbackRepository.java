package com.feedback.FeedbackService.Repository;

import com.feedback.FeedbackService.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
//        @Query("SELECT f FROM Feedback f WHERE f.userId = :userId")
        List<Feedback> findByUserId( Long userId);
}