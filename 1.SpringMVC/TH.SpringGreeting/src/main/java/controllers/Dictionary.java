package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RequestMapping(value = "/dictionary")
@Controller
public class Dictionary {
    @GetMapping("/home")
    public String home() {
        return "dictionary";
    }

    public HashMap tudien() {
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put("Hello", "Xin chào");
        dictionary.put("Book", "Quyển sách");
        dictionary.put("Goodbye", "Tạm biệt");
        return dictionary;
    }

    @GetMapping("/dictionary")
    public ModelAndView dictionary(@RequestParam("key") String english) {
        HashMap tudien = tudien();
        ModelAndView modelAndView = new ModelAndView("dictionary");
        String value = (String) tudien.get(english);
        if (value == null) {
            modelAndView.addObject("result", "Không có");
        } else {
            modelAndView.addObject("result", value);
        }
        return modelAndView;
    }
}
