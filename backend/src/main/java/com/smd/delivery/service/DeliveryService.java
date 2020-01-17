package com.smd.delivery.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService
{
    public DeliveryList getDeliveries()
    {
        DeliveryList deliveryList = new DeliveryList();
        deliveryList.setDeliveryList(DBAcessService.geInstance().getAllDeliveries());
        return deliveryList;
    }

    public Delivery getDeliveryById(int deliveryId)
    {
        return DBAcessService.geInstance().getDeliveryById(deliveryId);
    }

    public int createDelivery(String requestDelivery)
    {
        JsonObject customerInfo = JsonParser.parseString(requestDelivery).getAsJsonObject();
        int customerID = customerInfo.get("customerID").getAsInt();
        int deliveryID = DBAcessService.geInstance().createDelivery(customerID);
        return deliveryID;
    }

    public String deleteDelivery(int deliveryId)
    {
        DBAcessService.geInstance().deleteDelivery(deliveryId);
        String status = "OK";
        return status;
    }

    public String addItemToDelivery(String itemToAddInfo)
    {
        JsonObject itemInfo = JsonParser.parseString(itemToAddInfo).getAsJsonObject();
        int itemCode = itemInfo.get("itemCode").getAsInt();
        int deliveryId = itemInfo.get("deliveryId").getAsInt();
        DBAcessService.geInstance().addItemToDelivery(itemCode, deliveryId);
        String status = "OK";
        return status;
    }

    public String removeItemFromDelivery(int itemId)
    {
        DBAcessService.geInstance().removeItemFromDelivery(itemId);
        String status = "OK";
        return status;
    }

    public String removeItemsFromDelivery(String itemsToRemove)
    {
        JsonObject itemsInfo = JsonParser.parseString(itemsToRemove).getAsJsonObject();
        System.out.println(itemsInfo);
        List<Integer> idsToRemove = new ArrayList<>();
        JsonArray itemsArray = itemsInfo.get("items").getAsJsonArray();
        for(int i = 0; i < itemsArray.size(); i++)
        {
            idsToRemove.add(itemsArray.get(i).getAsInt());
        }
        DBAcessService.geInstance().removeItemsFromDelivery(idsToRemove);
        String status = "OK";
        return status;
    }
}
