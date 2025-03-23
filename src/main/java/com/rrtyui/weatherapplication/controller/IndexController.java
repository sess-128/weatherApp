package com.rrtyui.weatherapplication.controller;

import com.rrtyui.weatherapplication.entity.CustomSession;
import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.service.CookieService;
import com.rrtyui.weatherapplication.service.SessionService;
import com.rrtyui.weatherapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UserService userService;
    private final SessionService sessionService;
    private final CookieService cookieService;

    @Autowired
    public IndexController(UserService userService, SessionService sessionService, CookieService cookieService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.cookieService = cookieService;
    }

    @GetMapping
    public String index(HttpServletRequest httpServletRequest,
                        Model model) {

        String sessionId = httpServletRequest.getHeader("session_id");

        if (sessionId != null) {
            Optional<CustomSession> customSession = sessionService.findByUUID(sessionId);


            User user = customSession.get().getUser();
            model.addAttribute("user", user);

            return "index";
        }
        return "redirect:error";
    }

    @GetMapping("/logout")
    public String logout() {
        System.out.println("COOKIE DELETED");
        cookieService.delete();
        return "error";
    }


}
