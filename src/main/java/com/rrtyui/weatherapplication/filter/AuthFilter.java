package com.rrtyui.weatherapplication.filter;

import com.rrtyui.weatherapplication.service.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthFilter implements Filter {

    private final SessionService sessionService;

    public AuthFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();


        if (requestURI.equals("/sign-in") || requestURI.equals("/sign-up") || requestURI.startsWith("/static/")) {
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();

                    if (sessionService.isSessionValid(sessionId)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        httpServletResponse.sendRedirect("/sign-in");
    }
}