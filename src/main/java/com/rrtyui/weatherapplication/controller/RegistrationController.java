package com.rrtyui.weatherapplication.controller;

import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    private final AuthService authService;

    @Autowired
    public RegistrationController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showPageToRegistration() {
        return "sign-up";
    }

    @PostMapping
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult, HttpServletRequest httpServletRequest,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        authService.addUser(user);

        model.addAttribute(user);
        return "index";
    }
}
