package org.launchcode.controllers;


import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")

public class UserController {

    @RequestMapping(value = "add")
    public String add(Model model){
        model.addAttribute("title", "Signup");
        return "user/add";
    }


   /* @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model , @ModelAttribute User user, String verify) {
        model.addAttribute(user);

        return "user/add";
    }*/


}
