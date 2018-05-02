package com.example.groceryprices.controllers;

import com.example.groceryprices.models.*;
import com.example.groceryprices.models.data.AccountDAO;
import com.example.groceryprices.models.data.DepartmentDAO;
import com.example.groceryprices.models.data.ItemDAO;
import com.example.groceryprices.models.data.StoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private StoreDAO storeDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ItemDAO itemDAO;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){

        model.addAttribute("title","Item List");
        model.addAttribute("items",itemDAO.findAll());


        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }

        return "item/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addReceipt(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();

        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }
        model.addAttribute(new Item());

        model.addAttribute("title","Add Item");
        model.addAttribute("departments",departmentDAO.findAll());
        model.addAttribute("stores",storeDAO.findAll());

        return "item/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddItem(@ModelAttribute @Valid Item newItem, Errors errors, Model model, HttpServletRequest request, @RequestParam Integer departmentId, @RequestParam Integer storeId){

        HttpSession session = request.getSession();


        String user = session.getAttribute("mySession").toString();

        Account userAccount = accountDAO.findByUsername(user);

        String userName = userAccount.getUsername();


        if (errors.hasErrors()) {

            model.addAttribute("title", "Add Item");
            model.addAttribute("departments",departmentDAO.findAll());
            model.addAttribute("stores",storeDAO.findAll());
            model.addAttribute("account",userName);

            return "item/add";
        }

        Department dept = departmentDAO.findAllBydepartmentId(departmentId);
        Store newStore = storeDAO.findAllBystoreId(storeId);
        newItem.setDepartment(dept);
        newItem.setStore(newStore);
        newItem.setAccount(userAccount);


        model.addAttribute("title","Add Item");
        model.addAttribute("departments",departmentDAO.findAll());
        model.addAttribute("stores",storeDAO.findAll());
        model.addAttribute("account",userName);
        model.addAttribute("added","Item Added");




        itemDAO.save(newItem);
        return "item/add";

    }


}
