package com.rrtyui.weatherapplication.controller.auth;

import com.rrtyui.weatherapplication.entity.CustomSession;
import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.service.auth.CookieService;
import com.rrtyui.weatherapplication.service.auth.SessionService;
import com.rrtyui.weatherapplication.service.auth.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-in")
public class SignInController {
    private final UserService userService;
    private final SessionService sessionService;
    private final CookieService cookieService;
    private final HttpServletResponse httpServletResponse;

    @Autowired
    public SignInController(UserService userService, SessionService sessionService, CookieService cookieService, HttpServletResponse httpServletResponse) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.cookieService = cookieService;
        this.httpServletResponse = httpServletResponse;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @PostMapping
    public String signIn(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "sign-in";
        }

        User savedUser = userService.findByLogin(user);

        if (!BCrypt.checkpw(user.getPassword(), savedUser.getPassword())) {
            model.addAttribute("wrongCredentials", "Wrong password or login.");
            return "sign-in";
        }

        CustomSession customSession = sessionService.add(savedUser);

        cookieService.add(customSession, httpServletResponse);

        model.addAttribute(user);
        return "redirect:/";
    }
}
