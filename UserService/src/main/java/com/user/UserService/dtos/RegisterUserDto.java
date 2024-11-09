package com.user.UserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    private String fullName;
    private String username;
    private String password;
    private String email;
    private String designation;
    private String role;// This could be an enum or a string, depending on your implementation

}
