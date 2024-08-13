package com.sample.genzschool.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView displayErrorPage(Exception exception){
        ModelAndView errorpage = new ModelAndView();
        errorpage.setViewName("error");
        errorpage.addObject("errorMsg", exception.getMessage());
        return errorpage;
    }
}
