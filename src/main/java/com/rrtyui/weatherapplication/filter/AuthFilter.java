//package com.rrtyui.weatherapplication.filter;
//
//import com.rrtyui.weatherapplication.service.SessionService;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.HttpSessionRequiredException;
//
//import java.io.IOException;
//
//
//@WebFilter("/*")
//public class AuthFilter extends HttpFilter {
//
//
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        {
//            String requestURI = req.getRequestURI();
//            String sessionId = req.getHeader("session_id");
//
//            if (requestURI.equals("/sign-in") || requestURI.equals("/sign-up")) {
//                chain.doFilter(req, res);
//                return;
//            }
//            try {
//                if (sessionId == null || sessionId.isBlank() || sessionId.isEmpty()) {
//                    res.sendRedirect("/sign-in");
//                    return;
//                }
//            } catch (RuntimeException e) {
//                throw new HttpSessionRequiredException("Dont have active session");
//            }
//            chain.doFilter(req, res);
//            res.sendRedirect("/sign-in");
//        }
//    }
//}