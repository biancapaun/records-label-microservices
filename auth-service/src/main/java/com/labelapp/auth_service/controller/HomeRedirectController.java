package com.recordslabel.labelapp.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRedirectController {

    @GetMapping("/")
    public String redirectToAuth() {
        return "redirect:/register";
    }
}
