package io.github.pi.gerencestacionamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    
    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @GetMapping("/register")
    public String register() {
        return "register/index";
    }
}
