package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/word-clock")
    public String getTimeByTimezone(ModelMap modelMap, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        Date date = new Date();
        TimeZone local = TimeZone.getDefault();
        TimeZone locale = TimeZone.getTimeZone(city);
        long local_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        date.setTime(local_time);
        modelMap.addAttribute("city", city);
        modelMap.addAttribute("date", date);
        return "time";
    }
}
