package com.sample.genzschool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value={"","/","/home","/home.html"})
    public String displayHomePage(){
        return "home.html";
    }
}
