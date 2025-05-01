package com.labelapp.auth_service.controller;

import com.labelapp.auth_service.config.security.service.JWTService;
import com.labelapp.auth_service.config.security.service.UserService;
import com.labelapp.auth_service.dto.LoginRequestDTO;
import com.labelapp.auth_service.dto.RegisterRequestDTO;
import com.labelapp.auth_service.model.User;
import com.labelapp.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/user")
@EnableMethodSecurity(securedEnabled = true)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        if (!hasValidCredentials(loginRequest)) {
            return ResponseEntity.badRequest().body("Your credentials are not valid");
        }
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already used");
        }

        return userService.register(registerRequestDTO);
    }

    private boolean hasValidCredentials(LoginRequestDTO loginRequestDTO) {
        Optional<User> registeredUser = userRepository.findByEmail(loginRequestDTO.getEmail());

        AtomicBoolean isPasswordValid = new AtomicBoolean(false);
        registeredUser.ifPresent(user -> {
            String encodedPassword = user.getPassword();
            isPasswordValid.set(encoder.matches(loginRequestDTO.getPassword(), encodedPassword));
        });

        return isPasswordValid.get();
    }

}
