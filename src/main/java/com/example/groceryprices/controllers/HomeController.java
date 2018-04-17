package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.AccountService;
import com.example.groceryprices.models.data.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value="home")
public class HomeController {

    @Autowired
    private AccountDAO accountDAO;

    private AccountService accountService;

    @Autowired
    public HomeController(AccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("title","Grocery Prices");

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }

        return "home/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

}

