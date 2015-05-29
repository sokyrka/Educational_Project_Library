package com.work.controller;

import com.work.dao.factory.AdminDAOFactory;
import org.apache.log4j.Logger;
import com.work.entity.Request;
import com.work.service.AdminService;
import com.work.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Eugine Sokirka on 20.05.2015.
 */
@Controller
public class AdminController {

    private final static Logger logger = Logger.getLogger(AdminController.class);
    private AdminDAOFactory adminDAOFactory = new AdminDAOFactory();
    private AdminService adminService = new AdminServiceImpl(adminDAOFactory.getAdminDAO("JPA"));

    @RequestMapping(value = "/admin.html")
    public String adminCabinet(){
        logger.info("admin cabinet");
        return "admin/adminCabinet";
    }

    @RequestMapping(value = "/allRequests.html")
    public String allRequests(Model model){
        logger.info("all requests");
        List<Request> requestList = adminService.allRequests();
        model.addAttribute("requestList", requestList);
        return "admin/allRequests";
    }

    @RequestMapping(value = "/updatedRequest.html", method = RequestMethod.POST)
    public String updatedRequest(@RequestParam("radio_param") String radio_param,
                                 @RequestParam("request_id") int request_id){
        logger.info("update request");
        if(radio_param.equals("home")){
            adminService.updateRequest(request_id, 1, 0);
        }else {
            adminService.updateRequest(request_id, 0, 1);
        }
        return "admin/successfulUpdatedRequest";
    }

    @RequestMapping(value = "/addBook.html")
    public String addBook(){
        logger.info("add book");
        return "admin/addBook";
    }

    @RequestMapping(value = "/successfulAddedBook.html", method = RequestMethod.POST)
    public String successfulAddedBook(@RequestParam("title") String title,
                                      @RequestParam("author") String author,
                                      @RequestParam("year") int year,
                                      @RequestParam("pages") int pages){
        logger.info("success added book");
        adminService.addBook(title, author, year, pages);
        return "admin/successfulAddedBook";
    }

    @RequestMapping(value = "/deleteBook.html")
    public String deleteBook(){
        logger.info("delete book");
        return "admin/deleteBook";
    }

    @RequestMapping(value = "/successfulDeletedBook.html", method = RequestMethod.POST)
    public String successfulDeletedBook(@RequestParam("title") String title,
                                        @RequestParam("author") String author){
        logger.info("success deleted book");
        adminService.deleteBook(title, author);
        return "admin/successfulDeletedBook";
    }
}
