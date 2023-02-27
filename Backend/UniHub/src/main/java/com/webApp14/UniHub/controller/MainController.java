package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class MainController {
    @GetMapping(value={"/", "/main"})
    public String main(){
        return "main";
    }
}
