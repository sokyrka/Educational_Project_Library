package com.work.controller;

import com.work.common.Book;
import com.work.dao.UserDAOImpl;
import com.work.service.UserService;
import com.work.service.UserServiceImpl;
import org.apache.log4j.Logger;
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

    private final static Logger logger = Logger.getLogger(UserController.class);
    private UserService userService = new UserServiceImpl(new UserDAOImpl());

    @RequestMapping(value = "/welcomePage.html")
    public String welcomePage(){
        logger.info("welcome page");
        return "user/welcomePage";
    }

    @RequestMapping(value = "/userCabinet.html", method = RequestMethod.POST)
    public ModelAndView userCabinet(@RequestParam("login") String login,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        logger.info("user cabinet");
        ModelAndView modelAndView;
        if(userService.validateUser(login, password)){
            modelAndView = new ModelAndView("user/userCabinet");
            modelAndView.addObject("msg", login);
            session.setAttribute("user_login", login);
            logger.info("added login to session");
            session.setAttribute("user_pass", password);
            logger.info("added password to session");
            session.setMaxInactiveInterval(20*60);
        }else{
            modelAndView = new ModelAndView("user/welcomePage");
            modelAndView.addObject("msg","Incorrect login or pass");
            logger.info("incorrect login or password");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registerPage.html")
    public String registerPage(){
        logger.info("register page");
        return "user/registerPage";
    }

    @RequestMapping(value = "/successRegister.html", method = RequestMethod.POST)
    public ModelAndView successRegister(@RequestParam("first_name") String first_name,
                                        @RequestParam("second_name") String second_name,
                                        @RequestParam("login") String login,
                                        @RequestParam("password") String password,
                                        HttpSession session){
        logger.info("success register");
        ModelAndView modelAndView;
        if(!first_name.isEmpty() && !second_name.isEmpty() && !login.isEmpty() && !password.isEmpty()){
            if(!userService.validateUser(login, password)){
                userService.addUser(first_name, second_name, login, password);
                modelAndView = new ModelAndView("user/userCabinet");
                session.setAttribute("user_login", login);
                logger.info("added login to session");
                session.setMaxInactiveInterval(20*60);
                modelAndView.addObject("msg", login);
            }else {
                modelAndView = new ModelAndView("user/registerPage");
                modelAndView.addObject("msg", "Such user already exists");
                logger.info("such user already exists");
            }
        }else {
            modelAndView = new ModelAndView("user/registerPage");
            modelAndView.addObject("msg", "Incorrect information");
            logger.info("incorrect information");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/freeBooks.html")
    public String getAllFreeBooks(Model model){
        logger.info("get all free books");
        List<Book> bookList = userService.getAllFreeBook();
        model.addAttribute("books", bookList);
        return "user/allBooks";
    }

    @RequestMapping(value = "/findBook.html")
    public String findBook(){
        logger.info("find book");
        return "user/findBook";
    }

    @RequestMapping(value = "/foundBook.html", method = RequestMethod.POST)
    public String foundBook(@RequestParam("title") String title, Model model){
        logger.info("found book");
        String result;
        Book book = userService.findBook(title);
        if(!title.isEmpty() && book != null){
            model.addAttribute("book", book);
            result = "user/foundBook";
        }else {
            model.addAttribute("msg", "Not find");
            result = "user/findBook";
        }
        return result;
    }

    @RequestMapping(value = "/addedBook.html", method = RequestMethod.POST)
    public ModelAndView successfulAddedBook(@RequestParam("check") String title, HttpSession session){
        logger.info("success added book");
        final String login = (String) session.getAttribute("user_login");
        String[] tmpString  = title.split("[\\p{Punct} ]");
        for(String t : tmpString ){
            userService.addRequest(t,login);
        }
        ModelAndView modelAndView = new ModelAndView("user/successfulAddedBook");
        modelAndView.addObject("msg", title + " success added");
        return modelAndView;
    }

    @RequestMapping(value = "/usersBook.html")
    public String getAllUsersBook(Model model, HttpSession session){
        logger.info("get all users book");
        final String login = (String) session.getAttribute("user_login");
        List<Book> usersBook = userService.getUsersBook(login);
        model.addAttribute("usersBook", usersBook);
        return "user/usersBook";
    }

    @RequestMapping(value = "/deletedBook.html", method = RequestMethod.POST)
    public ModelAndView successfulDeletedBook(@RequestParam("check") String title, HttpSession session){
        logger.info("success deleted book");
        final String login = (String) session.getAttribute("user_login");
        String[] tmpString  = title.split("[\\p{Punct} ]");
        for(String t : tmpString ){
            userService.deleteUsersBook(t, login);
        }
        ModelAndView modelAndView = new ModelAndView("user/successfulDeletedBook");
        modelAndView.addObject("msg", title + " success deleted");
        return modelAndView;
    }
}
