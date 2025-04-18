package com.rrtyui.weatherapplication.controller.auth;

import com.rrtyui.weatherapplication.entity.CustomSession;
import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.service.auth.CookieService;
import com.rrtyui.weatherapplication.service.auth.SessionService;
import com.rrtyui.weatherapplication.service.auth.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
    private final UserService userService;
    private final SessionService sessionService;
    private final CookieService cookieService;
    private final HttpServletResponse httpServletResponse;

    @Autowired
    public SignUpController(UserService userService, SessionService sessionService, CookieService cookieService, HttpServletResponse httpServletResponse) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.cookieService = cookieService;
        this.httpServletResponse = httpServletResponse;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping
    public String signUp(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("repeatPassword") String repeatPassword,
                         Model model) {

        if (!user.getPassword().equals(repeatPassword)) {
            model.addAttribute("passwordMismatchError", "Passwords do not match.");
            return "sign-up";
        }
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        User savedUser = userService.add(user);

        CustomSession customSession = sessionService.add(savedUser);

        cookieService.add(customSession, httpServletResponse);

        model.addAttribute(user);
        return "redirect:/";
    }
}
