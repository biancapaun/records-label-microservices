package com.labelapp.ui.controller;

import com.google.common.net.HttpHeaders;
import com.labelapp.ui.client.AuthClient;
import com.labelapp.ui.dto.auth.LoginRequestDTO;
import com.labelapp.ui.dto.auth.LoginResponseDTO;
import com.labelapp.ui.dto.auth.RegisterRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthClient authClient;

    public AuthController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequestDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterRequestDTO registerRequest, Model model) {
        try {
            authClient.register(registerRequest);
            return "redirect:/auth/login";
        } catch (Exception ex) {
            model.addAttribute("error", "Registration failed. Try again.");
            return "auth/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginRequestDTO());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginRequestDTO loginRequestDTO,
                        HttpServletResponse response,
                        Model model) {
        try {
            ResponseEntity<LoginResponseDTO> loginResponse = authClient.login(loginRequestDTO);
            LoginResponseDTO loginResponseDTO = loginResponse.getBody();

            assert loginResponseDTO != null;
            ResponseCookie jwtCookie = ResponseCookie.from("jwt", loginResponseDTO.getToken())
                    .httpOnly(true)
                    .path("/")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            return "redirect:/artist";
        } catch (Exception ex) {
            model.addAttribute("error", "Login failed. Try again.");
            return "auth/login";
        }
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "error/access_denied";
    }
}

