package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.data.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value="signup")
public class SignupController {

    @Autowired
    private AccountDAO accountDAO;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String displaySignupForm(Model model){

        model.addAttribute("title","Sign Up");
        model.addAttribute(new Account());

        return "signup/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid Account newAccount, Errors errors, HttpSession session, Model model){

        if (errors.hasErrors()) {

            model.addAttribute("title", "Sign Up");

            return "signup/index";
        }

        if (newAccount.getPassword().equals(newAccount.getConfirmPassword())) {
            accountDAO.save(newAccount);
            session.setAttribute("mySession", newAccount.getUsername());
            return "redirect:/home";
        }else {

            model.addAttribute("title", "Sign Up");
            model.addAttribute("passError", "Passwords do not match");


            return "signup/index";
        }

    }
}
