package com.rem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rem.model.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long>{

}
