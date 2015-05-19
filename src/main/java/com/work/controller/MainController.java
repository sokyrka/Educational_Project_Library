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
public class MainController {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @RequestMapping(value = "/welcomePage.html", method = RequestMethod.GET)
    public ModelAndView welcomePage(){
        ModelAndView modelAndView = new ModelAndView("welcomePage");
        return modelAndView;
    }

    @RequestMapping(value = "/userCabinet.html", method = RequestMethod.POST)
    public ModelAndView userCabinet(@RequestParam("login") String login,
                                    @RequestParam("password") String password){
        ModelAndView modelAndView = null;
        if(userDAO.validateUser(login, password)){
            modelAndView = new ModelAndView("userCabinet");
        }else{
            modelAndView = new ModelAndView("welcomePage");
            modelAndView.addObject("msg","wrong");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/registerPage.html", method = RequestMethod.POST)
    public ModelAndView registerPage(){
        ModelAndView modelAndView = new ModelAndView("registerPage");
        return modelAndView;
    }


















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

    @RequestMapping(value = "/deleteForm.html", method = RequestMethod.GET)
    public ModelAndView deleteForm(@RequestParam("first_name") String first_name,
                                   @RequestParam("second_name") String second_name){
        userDAO.deleteUser(first_name, second_name);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
