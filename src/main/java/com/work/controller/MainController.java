package com.work.controller;

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
public class MainController {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @RequestMapping(value = "/form.html", method = RequestMethod.GET)
    public ModelAndView getForm(){
        ModelAndView modelAndView = new ModelAndView("Form");
        return modelAndView;
    }

    @RequestMapping(value = "/success.html", method = RequestMethod.GET)
    public ModelAndView successForm(@RequestParam("first_name") String first_name,
                                    @RequestParam("second_name") String second_name,
                                    @RequestParam("login") String login,
                                    @RequestParam("password") String password,
                                    @RequestParam("book_id") int book_id){

        userDAO.addUser(first_name, second_name, login, password, book_id);
        ModelAndView modelAndView = new ModelAndView("Success");
        modelAndView.addObject("msg", "Success");
        return modelAndView;
    }
}
