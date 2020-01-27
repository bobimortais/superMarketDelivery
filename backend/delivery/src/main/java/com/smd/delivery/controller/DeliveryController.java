package com.smd.delivery.controller;

import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryList;
import com.smd.delivery.error.ResponseError;
import com.smd.delivery.service.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@PropertySource("classpath:messages.properties")
@Api(value = "Delivery API")
public class DeliveryController
{
    private DeliveryService deliveryService;

    private static final Logger logger = Logger.getLogger(DeliveryController.class);

    @Value("${INTERNAL_ERROR_EXCEPTION_MESSAGE}")
    private String EXCEPTION_ERROR_MESSAGE;

    @Autowired
    public DeliveryController(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }

    @CrossOrigin
    @GetMapping("/getDeliveries")
    @ApiOperation(value = "Endpoint to get all deliveries")
    public ResponseEntity<DeliveryList> getDeliveries()
    {
        return new ResponseEntity<>(deliveryService.getDeliveries(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getDeliveries/{deliveryId}")
    @ApiOperation(value = "Endpoint to get a delivery by its id")
    public ResponseEntity<Delivery> getDelivery(@PathVariable(value="deliveryId") int deliveryId)
    {
        return new ResponseEntity<>(deliveryService.getDeliveryById(deliveryId), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createDelivery", consumes = {"application/json"})
    @ApiOperation(value = "Endpoint to create a new delivery")
    public ResponseEntity<String> createDelivery(@RequestBody(required = true) String requestDelivery)
    {
        int deliveryId = deliveryService.createDelivery(requestDelivery);
        return new ResponseEntity<>("Delivery " + deliveryId + " created.", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/addItemToDelivery", consumes = {"application/json"})
    @ApiOperation(value = "Endpoint to add an item to a given delivery")
    public ResponseEntity<String> addItemToDelivery(@RequestBody(required = true) String itemToAddInfo)
    {
        deliveryService.addItemToDelivery(itemToAddInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/removeItemFromDelivery")
    @ApiOperation(value = "Endpoint to remove an item to a given delivery")
    public ResponseEntity<String> removeItemFromDelivery(@RequestParam(value="itemId", required = true) int itemId)
    {
        deliveryService.removeItemFromDelivery(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/removeItemsFromDelivery")
    @ApiOperation(value = "Endpoint to remove several items from a given delivery")
    public ResponseEntity<String> removeItemsFromDelivery(@RequestBody(required = true) String requestItems)
    {
        deliveryService.removeItemsFromDelivery(requestItems);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteDelivery")
    @ApiOperation(value = "Endpoint to get all deliveries")
    public ResponseEntity<String> deleteDelivery(@RequestParam(value="deliveryId", required = true) int deliveryId)
    {
        deliveryService.deleteDelivery(deliveryId);
        return new ResponseEntity<>("Cancel delivery URL", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateDelivery")
    @ApiOperation(value = "Endpoint to update a delivery")
    public ResponseEntity<String> updateDelivery(@RequestBody(required = true)  String requestDelivery)
    {
        deliveryService.updateDelivery(requestDelivery);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Controller exception handler for Exception class
     */
    @ExceptionHandler({ Exception.class})
    public ResponseEntity<ResponseError> handleException(Exception e)
    {
        ResponseError internalErrorException = new ResponseError(new Date(),
                EXCEPTION_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
        logger.error(EXCEPTION_ERROR_MESSAGE, e);
        return new ResponseEntity<ResponseError>(internalErrorException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

