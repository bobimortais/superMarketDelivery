package com.smd.delivery.controller;

import com.smd.delivery.entity.DeliveryList;
import com.smd.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
