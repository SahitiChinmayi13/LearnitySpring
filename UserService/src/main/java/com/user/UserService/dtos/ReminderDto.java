package com.user.UserService.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReminderDto {
	
    private Long id;
//	@NotNull(message = "Reminder name cannot be null")
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
//    //private long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false) // Foreign key column
//    private UserDTO user;
//	public void setUser(User user2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//    public void setUser(UserDTO userDTO) {
//        this.user = new User();
//        this.user.setId(userDTO.getId());
//        this.user.setEmail(userDTO.getEmail());
//        this.user.setUsername(userDTO.getUsername());
//        this.user.setPassword(userDTO.getPassword());
//        this.user.setFullName(userDTO.getFullName());
//        this.user.setDesignation(userDTO.getDesignation());
//        this.user.setRole(userDTO.getRole());
//        
//    }
}
