package com.openclassrooms.mediscreen.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    public HomeController() {

    }

    @GetMapping("home")
    public String homePage() {
        return "/home";
    }
}
