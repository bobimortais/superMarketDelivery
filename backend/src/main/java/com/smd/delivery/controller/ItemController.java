package com.smd.delivery.controller;

import com.smd.delivery.entity.Item;
import com.smd.delivery.entity.ItemList;
import com.smd.delivery.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController
{
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @CrossOrigin
    @GetMapping("/getItems")
    public ResponseEntity<ItemList> getItems()
    {
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getItems/{itemCode}")
    public ResponseEntity<Item> getItems(@PathVariable(value="itemCode") int itemCode)
    {
        return new ResponseEntity<>(itemService.getItemByCode(itemCode), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createItem", consumes = {"application/json"})
    public ResponseEntity<String> createItem(@RequestBody(required = true) String requestItem)
    {
        int itemCode = itemService.createItem(requestItem);
        return new ResponseEntity<>("Item " + itemCode + " created.", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateItem", consumes = {"application/json"})
    public ResponseEntity<String> updateItem(@RequestBody(required = true) String requestItem)
    {
        return new ResponseEntity<>("Update item URL", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteItem")
    public ResponseEntity<String> deleteItem()
    {
        return new ResponseEntity<>("Delete item URL", HttpStatus.OK);
    }
}

