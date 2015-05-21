package com.work.controller;

import com.work.common.Book;
import com.work.dao.UserDAOImpl;
import com.work.service.UserService;
import com.work.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Eugine Sokirka on 15.05.2015.
 */
@Controller
public class UserController {

    private UserService userService = new UserServiceImpl(new UserDAOImpl());

    @RequestMapping(value = "/welcomePage.html")
    public String welcomePage(){
        return "welcomePage";
    }

    @RequestMapping(value = "/userCabinet.html", method = RequestMethod.POST)
    public ModelAndView userCabinet(@RequestParam("login") String login,
                                    @RequestParam("password") String password){
        ModelAndView modelAndView;
        if(userService.validateUser(login, password)){
            modelAndView = new ModelAndView("userCabinet");
            modelAndView.addObject("msg", login);
        }else{
            modelAndView = new ModelAndView("welcomePage");
            modelAndView.addObject("msg","Incorrect login or pass");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registerPage.html")
    public String registerPage(){
        return "registerPage";
    }

    @RequestMapping(value = "/successRegister.html", method = RequestMethod.POST)
    public ModelAndView successRegister(@RequestParam("first_name") String first_name,
                                        @RequestParam("second_name") String second_name,
                                        @RequestParam("login") String login,
                                        @RequestParam("password") String password){
        ModelAndView modelAndView;
        if(!first_name.isEmpty() && !second_name.isEmpty() && !login.isEmpty() && !password.isEmpty()){
            if(!userService.validateUser(login, password)){
                userService.addUser(first_name, second_name, login, password);
                modelAndView = new ModelAndView("userCabinet");
            }else {
                modelAndView = new ModelAndView("registerPage");
                modelAndView.addObject("msg", "Such user already exists");
            }
        }else {
            modelAndView = new ModelAndView("registerPage");
            modelAndView.addObject("msg", "Incorrect information");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/freeBooks.html")
    public String getAllFreeBooks(Model model){
        List<Book> bookList = userService.getAllFreeBook();
        model.addAttribute("books", bookList);
        return "allBooks";
    }

    @RequestMapping(value = "/findBook.html")
    public String findBook(){
        return "findBook";
    }

    @RequestMapping(value = "/foundBook.html", method = RequestMethod.POST)
    public String foundBook(@RequestParam("title") String title, Model model){
        String result;
        Book book = userService.findBook(title);
        if(!title.isEmpty() && book != null){
            model.addAttribute("book", book);
            result = "foundBook";
        }else {
            model.addAttribute("msg", "Not find");
            result = "findBook";
        }
        return result;
    }
}
