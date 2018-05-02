package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Department;
import com.example.groceryprices.models.Store;
import com.example.groceryprices.models.data.DepartmentDAO;
import com.example.groceryprices.models.data.StoreDAO;
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
@RequestMapping(value="store")
public class StoreController {

    @Autowired
    private StoreDAO storeDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("title", "Store Name");
        model.addAttribute("stores",storeDAO.findAll());

        model.addAttribute(new Store());

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")) {
            return "redirect:/login";
        }

        return "store/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid Store newStore, Errors errors, Model model){

        model.addAttribute("title", "Store Name");
        model.addAttribute("stores",storeDAO.findAll());

        if (errors.hasErrors()) {

            model.addAttribute("title", "Store Name");

            return "store/index";
        }

        storeDAO.save(newStore);
        return "store/index";

    }

    @RequestMapping(value="locator")
    public String findStore(Model model, HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("title","Map");


        return "store/locator";
    }

}
