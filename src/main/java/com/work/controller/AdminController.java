package com.work.controller;

import com.work.common.Request;
import com.work.dao.AdminDAOImpl;
import com.work.service.AdminService;
import com.work.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Eugine Sokirka on 20.05.2015.
 */
@Controller
public class AdminController {

    private AdminService adminService = new AdminServiceImpl(new AdminDAOImpl());

    @RequestMapping(value = "/admin.html")
    public String adminCabinet(){
        return "admin/adminCabinet";
    }

    @RequestMapping(value = "/allRequests.html")
    public String allRequests(Model model){
        List<Request> requestList = adminService.allRequests();
        model.addAttribute("requestList", requestList);
        return "admin/allRequests";
    }

    @RequestMapping(value = "/updatedRequest.html", method = RequestMethod.POST)
    public ModelAndView updatedRequest(@RequestParam("to_home") String home,
                                       @RequestParam("request_id") String request_id){
        ModelAndView modelAndView = new ModelAndView("admin/successfulUpdatedRequest");
        modelAndView.addObject("msg", home + " " + request_id);
        return modelAndView;
    }

    @RequestMapping(value = "/addBook.html")
    public String addBook(){
        return "admin/addBook";
    }

    @RequestMapping(value = "/successfulAddedBook.html", method = RequestMethod.POST)
    public String successfulAddedBook(@RequestParam("title") String title,
                                      @RequestParam("author") String author,
                                      @RequestParam("year") int year,
                                      @RequestParam("pages") int pages){
        adminService.addBook(title, author, year, pages);
        return "admin/successfulAddedBook";
    }

    @RequestMapping(value = "/deleteBook.html")
    public String deleteBook(){
        return "admin/deleteBook";
    }

    @RequestMapping(value = "/successfulDeletedBook.html", method = RequestMethod.POST)
    public String successfulDeletedBook(@RequestParam("title") String title,
                                        @RequestParam("author") String author){
        adminService.deleteBook(title, author);
        return "admin/successfulDeletedBook";
    }
}
