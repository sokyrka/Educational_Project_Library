package com.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Eugine Sokirka on 15.05.2015.
 */
@Controller
public class MainController {
    @RequestMapping(value = "form.html", method = RequestMethod.GET)
    public ModelAndView getForm(){
        ModelAndView modelAndView = new ModelAndView("Form");
        return modelAndView;
    }
}
