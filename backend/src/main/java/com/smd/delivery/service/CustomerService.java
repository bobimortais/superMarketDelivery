package com.smd.delivery.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.CustomerList;
import com.smd.delivery.entity.Item;
import com.smd.delivery.entity.ItemList;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    public CustomerList getCustomers()
    {
        CustomerList customerList = new CustomerList();
        customerList.setCustomerList(DBAcessService.geInstance().getAllCustomers());
        return customerList;
    }
}
