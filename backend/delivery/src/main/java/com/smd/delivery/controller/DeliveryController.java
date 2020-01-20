package com.smd.delivery.controller;

import com.smd.delivery.entity.Delivery;
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
    @GetMapping("/getDeliveries/{deliveryId}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable(value="deliveryId") int deliveryId)
    {
        return new ResponseEntity<>(deliveryService.getDeliveryById(deliveryId), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createDelivery", consumes = {"application/json"})
    public ResponseEntity<String> createDelivery(@RequestBody(required = true) String requestDelivery)
    {
        int deliveryId = deliveryService.createDelivery(requestDelivery);
        return new ResponseEntity<>("Delivery " + deliveryId + " created.", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/addItemToDelivery", consumes = {"application/json"})
    public ResponseEntity<String> addItemToDelivery(@RequestBody(required = true) String itemToAddInfo)
    {
        deliveryService.addItemToDelivery(itemToAddInfo);
        return new ResponseEntity<>("Add item to delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/removeItemFromDelivery")
    public ResponseEntity<String> removeItemFromDelivery(@RequestParam(value="itemId", required = true) int itemId)
    {
        deliveryService.removeItemFromDelivery(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/removeItemsFromDelivery")
    public ResponseEntity<String> removeItemsFromDelivery(@RequestBody(required = true) String requestItems)
    {
        deliveryService.removeItemsFromDelivery(requestItems);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteDelivery")
    public ResponseEntity<String> deleteDelivery(@RequestParam(value="deliveryId", required = true) int deliveryId)
    {
        deliveryService.deleteDelivery(deliveryId);
        return new ResponseEntity<>("Cancel delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateDelivery")
    public ResponseEntity<String> updateDelivery(@RequestBody(required = true)  String requestDelivery)
    {
        deliveryService.updateDelivery(requestDelivery);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

