package com.smd.delivery.controller;

import com.google.gson.JsonObject;
import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.DeliveryList;
import com.smd.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryController
{
    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }

    @CrossOrigin
    @GetMapping("/getDeliveries")
    public ResponseEntity<DeliveryList> getDeliveries()
    {
        return new ResponseEntity<>(deliveryService.getDeliveries(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createDelivery", consumes = {"application/json"})
    public ResponseEntity<String> createDelivery(@RequestBody(required = true) String requestDelivery)
    {
        deliveryService.createDelivery(requestDelivery);
        return new ResponseEntity<>("Create delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/addItemToDelivery")
    public ResponseEntity<String> addItemToDelivery()
    {
        return new ResponseEntity<>("Add item to delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/removeItemFromDelivery")
    public ResponseEntity<String> removeItemFromDelivery()
    {
        return new ResponseEntity<>("Remove item from delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/cancelDelivery")
    public ResponseEntity<String> cancelDelivery(@RequestParam(value="deliveryId", required = true) int deliveryId)
    {
        DBAcessService.geInstance().deleteDelivery(deliveryId);
        return new ResponseEntity<>("Cancel delivery URL", HttpStatus.OK);
    }
}

