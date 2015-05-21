package com.work.controller;

import com.work.common.Book;
import com.work.dao.UserDAOImpl;
import com.work.service.UserService;
import com.work.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
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
                                    @RequestParam("password") String password,
                                    HttpSession session){
        ModelAndView modelAndView;
        if(userService.validateUser(login, password)){
            modelAndView = new ModelAndView("userCabinet");
            modelAndView.addObject("msg", login);
            session.setAttribute("user_login", login);
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
                                        @RequestParam("password") String password,
                                        HttpSession session){
        ModelAndView modelAndView;
        if(!first_name.isEmpty() && !second_name.isEmpty() && !login.isEmpty() && !password.isEmpty()){
            if(!userService.validateUser(login, password)){
                userService.addUser(first_name, second_name, login, password);
                modelAndView = new ModelAndView("userCabinet");
                session.setAttribute("user_login", login);
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

    @RequestMapping(value = "/addedBook.html", method = RequestMethod.POST)
    public ModelAndView successfulAddedBook(@RequestParam("check") String title, HttpSession session){

        String login = (String) session.getAttribute("user_login");
        userService.addRequest(title,login);
        ModelAndView modelAndView = new ModelAndView("successfulAddedBook");
        modelAndView.addObject("msg", title + " success added");

        return modelAndView;
    }
}
