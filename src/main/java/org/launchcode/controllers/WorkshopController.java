package org.launchcode.controllers;



import org.launchcode.models.Workshop;
import org.launchcode.models.data.CustomerDao;

import org.launchcode.models.data.VehicleDao;
import org.launchcode.models.data.WorkshopDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class WorkshopController {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private WorkshopDao workshopDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("shops", workshopDao.findAll());
        model.addAttribute("title", "Shops");

        return "workshop/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Shop");
        model.addAttribute(new Workshop());
        return "workshop/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Workshop shop,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            model.addAttribute("menus", workshopDao.findAll());
            return "menu/add";
        }

        workshopDao.save(shop);
        return "redirect:view/" + shop.getId();
    }
    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewShop(Model model, @PathVariable int shopId) {

        Workshop shop = workshopDao.findOne(shopId);
        model.addAttribute("customer", shop.getCustomer());
        model.addAttribute("title", shop.getName());
        model.addAttribute("menuId", shop.getId());
        return "workshop/view";
    }

    /*@RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addShop(Model model, @PathVariable int shopId) {

        Workshop shop = workshopDao.findOne(shopId);
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";

    }
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add-item";
        }
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);

        return "redirect:/menu/view/" + theMenu.getId();
    }*/
}
