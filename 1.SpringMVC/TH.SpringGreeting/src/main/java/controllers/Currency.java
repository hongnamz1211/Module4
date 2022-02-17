package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Currency {
    @GetMapping("/home")
    public String index() {
        return "currency";
    }

    @GetMapping("/currency")
    public ModelAndView currencyConvert(@RequestParam int exchangeRate, @RequestParam double usd) {
        ModelAndView modelAndView = new ModelAndView("currency");
        double result = exchangeRate * usd;
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
