package com.smd.customer.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.customer.db.DBAcessService;
import com.smd.customer.entity.Customer;
import com.smd.customer.entity.CustomerList;
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

    public Customer getCustomerById(int customerId)
    {
        return DBAcessService.geInstance().getCustomerById(customerId);
    }

    public int createCustomer(String requestCustomer)
    {
        JsonObject customerInfo = JsonParser.parseString(requestCustomer).getAsJsonObject();
        String firstName = customerInfo.get("firstName").getAsString();
        String lastName = customerInfo.get("lastName").getAsString();
        String cpf = customerInfo.get("cpf").getAsString();
        String sex = customerInfo.get("sex").getAsString();
        String phone = customerInfo.get("phone").getAsString();
        int customerId = DBAcessService.geInstance().createCustomer(firstName, lastName, cpf, sex, phone);
        return customerId;
    }

    public int updateCustomer(String requestCustomer)
    {
        JsonObject customerInfo = JsonParser.parseString(requestCustomer).getAsJsonObject();
        int customerId = customerInfo.get("customerId").getAsInt();
        String firstName = customerInfo.get("firstName").getAsString();
        String lastName = customerInfo.get("lastName").getAsString();
        String cpf = customerInfo.get("cpf").getAsString();
        String sex = customerInfo.get("sex").getAsString();
        String phone = customerInfo.get("phone").getAsString();
        DBAcessService.geInstance().updateCustomer(customerId, firstName, lastName, cpf, sex, phone);
        return customerId;
    }

    public String deleteCustomer(int customerId)
    {
        String status = "OK";
        return status;
    }
}
