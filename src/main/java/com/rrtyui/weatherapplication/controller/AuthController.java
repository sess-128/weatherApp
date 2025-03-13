package com.rrtyui.weatherapplication.controller;

import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/sign-up")
    public String mainPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signIn(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return "sign-in";
        }

        Long userId = 2L;
        Cookie cookie = new Cookie("userId", userId.toString());
        httpServletResponse.addCookie(cookie);
        cookie.setPath("/");
        /**
         * Может в куки добавлять юзер айди и его сессию
         * или юзер айди и айди и также созданную сессию
          */

        userRepository.add(user);
        return "redirect:index";
    }
}
