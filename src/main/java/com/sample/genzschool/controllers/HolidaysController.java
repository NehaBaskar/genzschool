package com.sample.genzschool.controllers;

import com.sample.genzschool.Model.Holiday;
import com.sample.genzschool.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/holiday")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,
                                  Model model){

        model.addAttribute("festival",festival);
        model.addAttribute("federal", federal);
        List<Holiday> holidayList = holidaysRepository.findAll();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type: types) {
            model.addAttribute(type.toString(), (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holiday.html";
    }

}
