package com.smd.delivery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController
{
    @CrossOrigin
    @GetMapping("/getItems")
    public ResponseEntity<String> getItems()
    {
        return new ResponseEntity<>("Get items URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/createItem")
    public ResponseEntity<String> createItem()
    {
        return new ResponseEntity<>("Create item URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/updateItem")
    public ResponseEntity<String> updateItem()
    {
        return new ResponseEntity<>("Update item URL", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/deleteItem")
    public ResponseEntity<String> deleteItem()
    {
        return new ResponseEntity<>("Delete item URL", HttpStatus.OK);
    }
}

