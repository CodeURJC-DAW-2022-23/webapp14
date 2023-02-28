package com.webApp14.UniHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SurveyController {
    @GetMapping("/surveys")
    public String survey(Model model){
        return "surveys";
    }
}
