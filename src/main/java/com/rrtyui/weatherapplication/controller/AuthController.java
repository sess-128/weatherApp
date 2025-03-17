//package com.rrtyui.weatherapplication.controller;
//
//import com.rrtyui.weatherapplication.entity.User;
//import com.rrtyui.weatherapplication.service.AuthService;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//
////    private final UserRepository userRepository;
//    private final AuthService authService;
//
//    @Autowired
//    public AuthController(AuthService authService) {
////        this.userRepository = userRepository;
//        this.authService = authService;
//    }
//
//    @GetMapping("/sign-up")
//    public String mainPage(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//        HttpSession session = httpServletRequest.getSession();
////        session.setAttribute("UUID", authService.addSession());
//
//        Object uuid = session.getAttribute("UUID");
//
//        String uuidString = uuid.toString();
//
//        Cookie cookie = new Cookie("session_id", uuidString);
//        cookie.setMaxAge(24 * 60 * 60);
//
//        httpServletResponse.addCookie(cookie);
//
//        model.addAttribute("user", new User());
//        return "sign-up";
//    }
//
//    @PostMapping("/sign-up")
//    public String signIn(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            return "sign-up";
//        }
////        userRepository.add(user);
//        HttpSession session = httpServletRequest.getSession();
//
//
//        return "redirect:index";
//    }
//}
