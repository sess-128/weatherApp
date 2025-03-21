package com.rrtyui.weatherapplication.filter;

import com.rrtyui.weatherapplication.service.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

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

        if (requestURI.equals("/sign-in") || requestURI.equals("/sign-up")) {
            chain.doFilter(request, response);
            return;
        }
        Optional<String> sessionIdInCookies = sessionService.findSessionIdInCookies(httpServletRequest);

        if (sessionIdInCookies.isPresent()) {
            if (sessionService.isSessionValid(sessionIdInCookies.get())) {
                chain.doFilter(request, response);
                return;
            }
        }
        sessionService.deleteOldSessions();
        httpServletResponse.sendRedirect("/sign-in");
    }
}