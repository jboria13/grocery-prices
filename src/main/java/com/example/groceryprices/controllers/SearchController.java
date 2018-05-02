package com.example.groceryprices.controllers;

import com.example.groceryprices.models.Item;
import com.example.groceryprices.models.Search;
import com.example.groceryprices.models.data.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="search")
public class SearchController {

    @Autowired
    private ItemDAO itemDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("title", "Item Lookup");
        model.addAttribute("items",null);
        model.addAttribute(new Search());

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")) {
            return "redirect:/login";
        }

        return "search/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String searchResults(@RequestParam(value = "searchItemName") @ModelAttribute @Valid Search newSearch, Model model, HttpServletRequest request) {


        String searchName = newSearch.getSearchItemName();
        List<Item> searchResults = itemDAO.findAllByItemName(searchName);
        model.addAttribute("title", "Item Lookup");

        if (searchResults.isEmpty()){
            model.addAttribute("items",null);
            model.addAttribute("results", "No Results Found");
        }else {

            model.addAttribute("items", searchResults);

        }

        HttpSession session = request.getSession();
        if (null == session.getAttribute("mySession")){
            return "redirect:/login";
        }

        return "search/index";
    }
}
