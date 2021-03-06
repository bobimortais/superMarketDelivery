package com.smd.delivery.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryItem;
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

    public DeliveryItem addItemToDelivery(String itemToAddInfo)
    {
        JsonObject itemInfo = JsonParser.parseString(itemToAddInfo).getAsJsonObject();
        System.out.println("Item to add:\n" + itemToAddInfo);
        int itemCode = itemInfo.get("itemCode").getAsInt();
        int deliveryId = itemInfo.get("deliveryId").getAsInt();
        DeliveryItem deliveryItem = DBAcessService.geInstance().addItemToDelivery(itemCode, deliveryId);
        return deliveryItem;
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

    public String updateDelivery(String deliveryData)
    {
        JsonObject deliveryInfo = JsonParser.parseString(deliveryData).getAsJsonObject();
        int deliveryId = deliveryInfo.get("deliveryId").getAsInt();
        int customerId = deliveryInfo.get("customerId").getAsInt();
        String deliveryStatus = deliveryInfo.get("status").getAsString();
        System.out.println(deliveryData);
        DBAcessService.geInstance().updateDelivery(deliveryId, customerId, deliveryStatus);
        String status = "OK";
        return status;
    }
}
