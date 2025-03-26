package com.rrtyui.weatherapplication.service.auth;

import com.rrtyui.weatherapplication.entity.CustomSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    public CookieService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void add(CustomSession customSession, HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("session_id", customSession.getId().toString());
        cookie.setMaxAge(60 * 60 * 24);
        httpServletResponse.addCookie(cookie);
    }

    public void delete() {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("session_id".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                httpServletResponse.addCookie(cookie);
            }
        }
    }
}
