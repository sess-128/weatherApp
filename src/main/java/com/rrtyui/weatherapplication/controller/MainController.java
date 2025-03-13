package com.rrtyui.weatherapplication.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class MainController {

    @GetMapping
    public String showMainPage(Model model,
                               HttpServletRequest httpServletRequest) {
        var cookies = httpServletRequest.getCookies();
        String targetCookie = "userId";

        Cookie findable = null;
        for (Cookie cookie : cookies) {
            if(targetCookie.equals(cookie.getName())){
                findable = cookie;
            }
        }

        model.addAttribute(targetCookie, findable.getValue());
        return "index";
    }
}
