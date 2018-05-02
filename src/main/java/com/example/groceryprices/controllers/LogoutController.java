package com.example.groceryprices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "logout")
class LogoutController {

    @RequestMapping(value="",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:login";
    }

}
