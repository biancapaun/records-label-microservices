package com.labelapp.ui.client;

import com.labelapp.ui.dto.auth.LoginRequestDTO;
import com.labelapp.ui.dto.auth.LoginResponseDTO;
import com.labelapp.ui.dto.auth.RegisterRequestDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @PostMapping("/user/login")
    ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest);


    @PostMapping("/user/register")
    ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO);

}
