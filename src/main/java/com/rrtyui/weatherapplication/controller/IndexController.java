package com.rrtyui.weatherapplication.controller;

import com.rrtyui.weatherapplication.entity.Session;
import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.service.CookieService;
import com.rrtyui.weatherapplication.service.SessionService;
import com.rrtyui.weatherapplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index(@ModelAttribute("user") User user , HttpServletRequest httpServletRequest,
                        Model model) {

        String sessionId = "";

        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("session_id".equals(cookie.getName())) {
                sessionId = cookie.getValue();
            }
        }

        Session byUUID = sessionService.findByUUID(sessionId);
        User user1 = byUUID.getUser();

        model.addAttribute("user", user1);
        return "index";
    }
}
