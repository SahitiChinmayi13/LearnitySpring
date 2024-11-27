package com.rem.service;
import com.rem.model.Reminder;
import com.rem.repo.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    // Create a new reminder
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    // Get all reminders
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    // Get reminder by ID
    public Optional<Reminder> getReminderById(Long id) {
        return reminderRepository.findById(id);
    }

    // Update reminder
    public Reminder updateReminder(Long id, Reminder updatedReminder) {
        if (reminderRepository.existsById(id)) {
            updatedReminder.setId(id);
            return reminderRepository.save(updatedReminder);
        }
        return null;
    }

    // Delete reminder by ID
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
}

