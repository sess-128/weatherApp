package com.rrtyui.weatherapplication.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class SessionManager implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("session_id");
        boolean permission = getPermission(sessionId);

        if (permission) {
            return true;
        } else {
            response.sendRedirect("/sign-in");
        }
        return false;
    }

    private boolean getPermission(String sessionId) {
        return sessionId != null;
    }
}
