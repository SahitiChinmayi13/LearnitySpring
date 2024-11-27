package com.rem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reminder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String reminderName; 
	private long userId;
    public enum ReminderType {
        DAILY,
        WEEKLY,
        ONCE
    }
    private ReminderType reminderType; 
    private LocalTime time;            
    private String dayOfWeek; 
    private LocalDate date;
    private String email;
}
