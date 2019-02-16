package org.launchcode.models.forms;

import org.launchcode.models.Customer;
import org.launchcode.models.Workshop;

import javax.validation.constraints.NotNull;

public class AddCustomerForm {


    private Workshop shop;

    private Iterable<Customer> customers;

    @NotNull
    private int shopId;

    @NotNull
    private int customerId;


    public AddCustomerForm() {
    }

    public AddCustomerForm(Workshop shop, Iterable<Customer> customers) {
        this.shop = shop;
        this.customers = customers;
    }

    public Workshop getShop() {
        return shop;
    }

    public void setShop(Workshop shop) {
        this.shop = shop;
    }

    public Iterable<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Iterable<Customer> customers) {
        this.customers = customers;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
