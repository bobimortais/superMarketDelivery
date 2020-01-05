package com.smd.delivery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController
{
    @CrossOrigin
    @GetMapping("/getCustomers")
    public ResponseEntity<String> getCustomers()
    {
        return new ResponseEntity<>("Get Customers URL", HttpStatus.OK);
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

