package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.Item;
import com.example.groceryprices.models.ShoppingList;
import com.example.groceryprices.models.data.AccountDAO;
import com.example.groceryprices.models.data.ItemDAO;
import com.example.groceryprices.models.data.ShoppingListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value="home")
public class HomeController {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ShoppingListDAO shoppingListDAO;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }

        String user = session.getAttribute("mySession").toString();

        Account userAccount = accountDAO.findByUsername(user);
        List<ShoppingList> myList = shoppingListDAO.findByAccount(userAccount);

        model.addAttribute("lists",myList);
        model.addAttribute("title", "Welcome, " + user );



        return "home/index";
    }


}

