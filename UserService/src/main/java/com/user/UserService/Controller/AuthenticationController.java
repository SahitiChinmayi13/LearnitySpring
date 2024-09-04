package com.user.UserService.Controller;


import com.user.UserService.Model.User;
import com.user.UserService.Service.AuthenticationService;
import com.user.UserService.Service.JwtService;
import com.user.UserService.dtos.LoginUserDto;
import com.user.UserService.dtos.RegisterUserDto;
import com.user.UserService.responses.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Optional<User> authenticatedUser = authenticationService.authenticate(loginUserDto);
        User user = authenticatedUser.get();
        String jwtToken = jwtService.generateToken(authenticatedUser.get());
        String role = String.valueOf(user.getRole());
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).setRole(role);;

        return ResponseEntity.ok(loginResponse);
    }
}