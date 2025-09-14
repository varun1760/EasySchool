package com.rao.EasySchool.controllers;

import com.rao.EasySchool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @GetMapping("/holidays/{display}")
    public String displayHoliday(@PathVariable String display, Model model) {

        if (null != display && display.equals("all")) {
            model.addAttribute("federal", true);
            model.addAttribute("festival", true);
        } else if (null != display && display.equals("festival")) {
            model.addAttribute("federal", false);
            model.addAttribute("festival", true);
        } else if (null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
            model.addAttribute("festival", false);
        }
        List<Holiday> holidays = Arrays.asList(
                new Holiday("Jan 1, 2025", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday("Jan 26, 2025", "Republic day", Holiday.Type.FEDERAL),
                new Holiday("April 14, 2025", "Dr. B.R. Ambedkar Jayanti", Holiday.Type.FEDERAL),
                new Holiday("May 1, 2025", "Labour Day", Holiday.Type.FEDERAL),
                new Holiday("August 15, 2025", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday("October 2, 2025", "Mahatma Gandhi Jayanti", Holiday.Type.FEDERAL),
                new Holiday("October 20, 2025", "Diwali", Holiday.Type.FESTIVAL),
                new Holiday("December 25", "Christmas Day", Holiday.Type.FESTIVAL)
        );
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}
