package org.launchcode.controllers;


import org.launchcode.models.Customer;

import org.launchcode.models.data.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("title", "Customers");

        return "customer/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Customer");
        model.addAttribute(new Customer());
       // model.addAttribute("category", categoryDao.findAll());
        return "customer/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Customer customer, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Customer");
            return "category/add";
        }

        customerDao.save(customer);
        return "redirect:";
    }
}

