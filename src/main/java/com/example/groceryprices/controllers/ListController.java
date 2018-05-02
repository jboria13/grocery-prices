package com.example.groceryprices.controllers;

import com.example.groceryprices.models.*;
import com.example.groceryprices.models.data.AccountDAO;
import com.example.groceryprices.models.data.ItemDAO;
import com.example.groceryprices.models.data.ShoppingListDAO;
import com.example.groceryprices.models.data.StoreDAO;
import com.example.groceryprices.models.forms.AddShoppingListItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("shoppinglist")
public class ListController {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private StoreDAO storeDAO;

    @Autowired
    private ShoppingListDAO shoppingListDAO;

    @Autowired
    private ItemDAO itemDAO;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }

        String user = session.getAttribute("mySession").toString();

        Account userAccount = accountDAO.findByUsername(user);

        List<ShoppingList> myShoppingList = shoppingListDAO.findByAccount(userAccount);

        model.addAttribute("title","New Shopping List");
        model.addAttribute("shoppingLists", myShoppingList);
        model.addAttribute("stores",storeDAO.findAll());
        model.addAttribute(new ShoppingList());

        return "shoppinglist/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String createShoppingList(@ModelAttribute @Valid ShoppingList newShoppingList, Errors errors, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();

        String user = session.getAttribute("mySession").toString();

        Account userAccount = accountDAO.findByUsername(user);

        String userName = userAccount.getUsername();

        if (errors.hasErrors()) {

            model.addAttribute("title", "New Shopping List");
            model.addAttribute("stores",storeDAO.findAll());
            model.addAttribute("account",userName);

            return "shoppinglist/index";
        }

        newShoppingList.setAccount(userAccount);

        shoppingListDAO.save(newShoppingList);

        List<ShoppingList> myShoppingList = shoppingListDAO.findByAccount(userAccount);

        model.addAttribute("title","New Shopping List");
        model.addAttribute("stores",storeDAO.findAll());
        model.addAttribute("account",userName);
        model.addAttribute("created","List Created!");
        model.addAttribute("shoppingLists", myShoppingList);



        return "shoppinglist/index";

    }

    @RequestMapping (value = "view/{shoppingListId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int shoppingListId){

        ShoppingList shopList = shoppingListDAO.findByshoppingListId(shoppingListId);

        model.addAttribute("title", shopList.getShoppingListName());
        model.addAttribute("items", shopList.getItems());
        model.addAttribute("shoppingListId", shopList.getShoppingListId());

        return"shoppinglist/view";

    }

    @RequestMapping( value = "add-item/{shoppingListId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int shoppingListId){

        ShoppingList shoppingList = shoppingListDAO.findByshoppingListId(shoppingListId);

        AddShoppingListItemForm form = new AddShoppingListItemForm(shoppingList);

        model.addAttribute("title", "Add item to shopping list " + shoppingList.getShoppingListName());
        model.addAttribute("form", form);
        model.addAttribute("items",itemDAO.findAll());

        return "shoppinglist/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddShoppingListItemForm form, Errors errors, Model model, HttpServletRequest request,  @RequestParam(required = false) int[] itemIds) {

        HttpSession session = request.getSession();

        String user = session.getAttribute("mySession").toString();

        Account userAccount = accountDAO.findByUsername(user);

        String userName = userAccount.getUsername();

        if (errors.hasErrors()) {

            model.addAttribute("title", "New Shopping List");
            model.addAttribute("stores",storeDAO.findAll());
            model.addAttribute("account",userName);

            return "shoppinglist/index";
        }

        ShoppingList theShoppingList = shoppingListDAO.findByshoppingListId(form.getShoppingListId());


        for (int itemId : itemIds){
            Item addedItem = itemDAO.findByitemId(itemId);
            theShoppingList.addItem(addedItem);
            shoppingListDAO.save(theShoppingList);
        }

       return "redirect:view/" + form.getShoppingListId();
    }

}
