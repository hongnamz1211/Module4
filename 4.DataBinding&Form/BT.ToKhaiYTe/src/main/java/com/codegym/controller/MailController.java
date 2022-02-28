package com.codegym.controller;

import com.codegym.model.Mail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("mail", new Mail());
        modelAndView.addObject("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        modelAndView.addObject("size", new String[]{"5", "10", "15", "25", "50", "100"});
        modelAndView.addObject("filter", new String[]{"Enable spams filter"});
        modelAndView.addObject("signature", new String[]{null});
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createMail(@ModelAttribute("mail") Mail mail) {
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("mail", mail);
        return modelAndView;
    }


}
