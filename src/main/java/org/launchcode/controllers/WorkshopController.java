package org.launchcode.controllers;



import org.launchcode.models.Customer;
import org.launchcode.models.Workshop;
import org.launchcode.models.data.CustomerDao;

import org.launchcode.models.data.VehicleDao;
import org.launchcode.models.data.WorkshopDao;

import org.launchcode.models.forms.AddCustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.validation.Valid;

@Controller
@RequestMapping("workshop")
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
            model.addAttribute("title", "Add Shop");
            model.addAttribute("shops", workshopDao.findAll());
            return "workshop/add";
        }

        workshopDao.save(shop);
        return "redirect:view/" + shop.getId();
    }
    @RequestMapping(value = "view/{shopId}", method = RequestMethod.GET)
    public String viewShop(Model model, @PathVariable int shopId) {

        Workshop shop = workshopDao.findOne(shopId);
        model.addAttribute("customers", shop.getCustomer());
        model.addAttribute("title", shop.getName());
        model.addAttribute("shopId", shop.getId());
        return "workshop/view";
    }

    @RequestMapping(value = "add-customer/{shopId}", method = RequestMethod.GET)
    public String addShop(Model model, @PathVariable int shopId) {

        Workshop shop = workshopDao.findOne(shopId);
        AddCustomerForm form = new AddCustomerForm(shop, customerDao.findAll());

        model.addAttribute("title", "Add customer to Shop: " + shop.getName());
        model.addAttribute("form", form);
        return "workshop/add-customer";

    }
    @RequestMapping(value = "add-customer", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddCustomerForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Customer");
            return "workshop/add-customer";
        }
        Customer theCustomer = customerDao.findOne(form.getCustomerId());
        Workshop theShop = workshopDao.findOne(form.getShopId());
        theShop.addCustomer(theCustomer);
        workshopDao.save(theShop);

        return "redirect:/workshop/view/" + theShop.getId();
    }
}
