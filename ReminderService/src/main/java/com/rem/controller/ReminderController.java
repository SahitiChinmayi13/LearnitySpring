package com.rem.controller;

import com.rem.model.Reminder;
import com.rem.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    // Create a new reminder
    @PostMapping("/create")
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    // Get all reminders
    @GetMapping("/allReminders")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = reminderService.getAllReminders();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    // Get a reminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Long id) {
        Optional<Reminder> reminder = reminderService.getReminderById(id);
        return reminder.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a reminder
    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id, @RequestBody Reminder updatedReminder) {
        Reminder reminder = reminderService.updateReminder(id, updatedReminder);
        if (reminder != null) {
            return new ResponseEntity<>(reminder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a reminder
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
