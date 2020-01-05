package com.smd.delivery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class CustomerList
{
    @JsonProperty("customers")
    private List<Customer> customerList;

    public CustomerList()
    {
        customerList = new ArrayList<Customer>();
    }
    public List<Customer> getCustomerList()
    {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList)
    {
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer)
    {
        this.customerList.add(customer);
    }
}
