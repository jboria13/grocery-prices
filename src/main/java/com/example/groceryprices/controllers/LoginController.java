package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.AccountService;
import com.example.groceryprices.models.Login;
import com.example.groceryprices.models.data.AccountDAO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value="login")
public class LoginController {

    private AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    private AccountDAO accountDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title", "Login");
        model.addAttribute(new Login());

        return "login/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid Login login, HttpSession session, Errors errors, Model model) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Login");

            return "login/index";
        }

        Account validAccount = accountService.findByUsername(login.getLoginName());

        if (validAccount == null || !validAccount.getPassword().equals(login.getLoginPassword())) {
            model.addAttribute("title", "Login");
            model.addAttribute("dupError", "Invalid Username or Password");

            model.addAttribute(new Login());

            return "login/index";
        }

        session.setAttribute("mySession", login.getLoginName());

        return "redirect:/home";

    }
}
