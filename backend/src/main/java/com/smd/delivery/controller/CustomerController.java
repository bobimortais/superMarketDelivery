package com.smd.delivery.controller;

import com.smd.delivery.entity.CustomerList;
import com.smd.delivery.service.CustomerService;
import com.smd.delivery.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController
{
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @CrossOrigin
    @GetMapping("/getCustomers")
    public ResponseEntity<CustomerList> getCustomers()
    {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer()
    {
        return new ResponseEntity<>("Create customer URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer()
    {
        return new ResponseEntity<>("Update customer URL", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer()
    {
        return new ResponseEntity<>("Delete customer URL", HttpStatus.OK);
    }
}

