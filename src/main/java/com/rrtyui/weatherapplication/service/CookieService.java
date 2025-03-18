package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.entity.Session;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    public void add(Session session, HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("session_id", session.getId().toString());
        cookie.setMaxAge(60 * 60 * 24);
        httpServletResponse.addCookie(cookie);
    }
}
