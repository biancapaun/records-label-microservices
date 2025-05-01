package com.labelapp.auth_service.config.security.service;

import com.labelapp.auth_service.config.security.UserDetailsImplementation;
import com.labelapp.auth_service.dto.LoginRequestDTO;
import com.labelapp.auth_service.dto.LoginResponseDTO;
import com.labelapp.auth_service.dto.MessageResponse;
import com.labelapp.auth_service.dto.RegisterRequestDTO;
import com.labelapp.auth_service.enums.Role;
import com.labelapp.auth_service.model.User;
import com.labelapp.auth_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService jwtHelper;

    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserDetailsImplementation userDetails = UserDetailsImplementation.build(user);

        ResponseCookie jwtCookie = jwtHelper.generateJwtCookie(userDetails);

        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new LoginResponseDTO(userDetails.getEmail(), jwtCookie.getValue(), userDetails.getId(), role));
    }

    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {

        String role = registerRequestDTO.getRole();

        if (role == null || !(role.toUpperCase(Locale.ROOT).equals("ADMIN") || role.toUpperCase(Locale.ROOT).equals("GUEST"))) {
            throw new RuntimeException("Invalid role");
        }

        User user = new User(null, registerRequestDTO.getEmail(), encoder.encode(registerRequestDTO.getPassword()), registerRequestDTO.getFirstName(), registerRequestDTO.getLastName(), Role.valueOf(role));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}