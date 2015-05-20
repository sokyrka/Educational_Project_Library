package com.work.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.work.dao.UserDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Eugine Sokirka on 15.05.2015.
 */
@Controller
public class UserController {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @RequestMapping(value = "/welcomePage.html")
    public String welcomePage(){
        return "welcomePage";
    }

    @RequestMapping(value = "/userCabinet.html", method = RequestMethod.POST)
    public ModelAndView userCabinet(@RequestParam("login") String login,
                                    @RequestParam("password") String password){
        ModelAndView modelAndView = null;
        if(userDAO.validateUser(login, password)){
            modelAndView = new ModelAndView("userCabinet");
        }else{
            modelAndView = new ModelAndView("redirect:/welcomePage.html");
            modelAndView.addObject("msg","Incorrect login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/registerPage.html")
    public String registerPage(){
        return "registerPage";
    }

}
