package com.group.greatreadsbe.controller;

import com.group.greatreadsbe.config.security.service.JWTService;
import com.group.greatreadsbe.config.security.service.UserService;
import com.group.greatreadsbe.dto.auth.LoginRequestDTO;
import com.group.greatreadsbe.dto.auth.RegisterRequestDTO;
import com.group.greatreadsbe.model.User;
import com.group.greatreadsbe.persistance.BookRepository;
import com.group.greatreadsbe.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/user")
@EnableGlobalMethodSecurity(securedEnabled = true)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllRegisteredUsers() {
        return userRepository.findAll();
    }

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
