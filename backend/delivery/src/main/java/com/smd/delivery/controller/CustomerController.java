package com.smd.delivery.controller;

import com.smd.delivery.entity.Customer;
import com.smd.delivery.entity.CustomerList;
import com.smd.delivery.service.CustomerService;
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
    @GetMapping("/getCustomers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value="customerId") int customerId)
    {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody(required = true) String requestCustomer)
    {
        int customerId = customerService.createCustomer(requestCustomer);
        return new ResponseEntity<>("Customer " + customerId + " created.", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody(required = true) String requestCustomer)
    {
        int customerId = customerService.updateCustomer(requestCustomer);
        return new ResponseEntity<>("Customer " + customerId + " updated.", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam(value="customerId", required = true) int customerId)
    {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Delete customer URL", HttpStatus.OK);
    }
}

