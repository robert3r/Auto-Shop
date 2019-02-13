package org.launchcode.controllers;


import org.launchcode.models.Customer;
import org.launchcode.models.Vehicle;

import org.launchcode.models.data.CustomerDao;
import org.launchcode.models.data.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private CustomerDao customerDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("vehicles", vehicleDao.findAll());
        model.addAttribute("title", "Vehicles");

        return "vehicle/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddVehicleForm(Model model) {
        model.addAttribute("title", "Add Vehicle");
        model.addAttribute("categories", customerDao.findAll());
        model.addAttribute(new Vehicle());
        return "vehicle/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddVehicleForm(@ModelAttribute  @Valid Vehicle newVehicle,
                                       Errors errors, @RequestParam int customerId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", customerDao.findAll());
            return "cheese/add";
        }
        Customer cust = customerDao.findOne(customerId);
        newVehicle.setCustomer(cust);
        vehicleDao.save(newVehicle);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveVehicleForm(Model model) {
        model.addAttribute("vehicles", vehicleDao.findAll());
        model.addAttribute("title", "Remove Vehicle");
        return "vehicle/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveVehicleForm(@RequestParam int[] vehicleIds) {

        for (int vehicleId : vehicleIds) {
            vehicleDao.delete(vehicleId);
        }

        return "redirect:";
    }
    @RequestMapping(value = "customer", method = RequestMethod.GET)
    public String customer(Model model, @RequestParam int id) {

        Customer cust = customerDao.findOne(id);
        List<Vehicle> vehicles = cust.getVehicles();
        model.addAttribute("vehicles", vehicles );
        model.addAttribute("title", "Customer's vehicles: " + cust.getName());
        return "vehicle/index";
    }

}
