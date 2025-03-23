package com.rrtyui.weatherapplication.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class SessionManager implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/sign-in") || requestURI.equals("/sign-up")) {
            return true;
        }
        String sessionId = getSessionIdFromCookies(request);
        boolean permission = getPermission(sessionId);

        if (permission) {
            return true;
        } else {
            response.sendRedirect("/sign-in");
        }
        return false;
    }

    private String getSessionIdFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private boolean getPermission(String sessionId) {
        return sessionId != null;
    }
}
