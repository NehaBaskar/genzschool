package com.sample.genzschool.controllers;

import com.sample.genzschool.Model.Contact;
import com.sample.genzschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @RequestMapping(value = "/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

  /*  @PostMapping(value="/saveForm")
    public ModelAndView saveContactForm(@RequestParam String name, @RequestParam String email,@RequestParam String subject,
                                        @RequestParam String mobileNo, @RequestParam String message){
        log.info("Name : " + name);
        log.info("Email : "+ email);
        log.info("Subject : "+ subject);
        log.info("Mobile Number : "+ mobileNo);
        log.info("Message : "+message);

        return new ModelAndView("redirect:/contact");
    }*/

    @PostMapping("/saveForm")
    public String saveContactForm(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        System.out.println("Entered ContactController....");
        if(errors.hasErrors()){
            log.error("Contact Form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactService.saveContact(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactService.findContactMsgswithStatusOpen();
        ModelAndView modelAndView = new ModelAndView("message.html");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg", method= RequestMethod.GET)
    public String closeMsgStatus(@RequestParam int id ){
       contactService.updateStatus(id);
       return "redirect:/displayMessages";
    }
}

