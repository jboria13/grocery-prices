package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.Department;
import com.example.groceryprices.models.data.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentDAO departmentDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("title", "Grocery Departments");
        model.addAttribute(new Department());

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")) {
            return "redirect:/login";
        }

        return "department/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String addDepartment(@ModelAttribute @Valid Department newDepartment,  Errors errors, Model model){

        model.addAttribute("title", "Grocery Departments");

        if (errors.hasErrors()) {

            model.addAttribute("title", "Sign Up");

            return "department/index";
        }

        departmentDAO.save(newDepartment);
        return "department/index";

    }



}
