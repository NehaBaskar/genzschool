package com.sample.genzschool.controllers;

import com.sample.genzschool.Model.Person;
import com.sample.genzschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashBoard(Model model, Authentication authentication, HttpSession httpSession){
        Person person = personRepository.findByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        //throw new RuntimeException("Its been Bad day..");
        httpSession.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
