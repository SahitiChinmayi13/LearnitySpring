package com.user.UserService.Service;

import com.user.UserService.Entity.Role;
import com.user.UserService.Entity.User;
import com.user.UserService.Repository.UserRepository;
import com.user.UserService.dtos.LoginUserDto;
import com.user.UserService.dtos.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // Create a new User object and set its fields based on the RegisterUserDto
        User user = new User();
        user.setFullName(input.getFullName());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setDesignation(input.getDesignation());
        user.setRole(Role.valueOf(input.getRole().toUpperCase())); // Assuming Role is an enum

        // Save the user in the database
        return userRepository.save(user);
    }

    public Optional<User> authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()
            )
        );
        System.out.println(input.getUsername());
        return userRepository.findByUsername(input.getUsername());
    }

}

