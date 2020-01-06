package com.smd.delivery.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryList;
import com.smd.delivery.entity.Item;
import com.smd.delivery.entity.ItemList;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{
    public ItemList getItems()
    {
        ItemList itemList = new ItemList();
        itemList.setItemList(DBAcessService.geInstance().getAllItems());
        return itemList;
    }

    public Item getItemByCode(int itemCode)
    {
        return DBAcessService.geInstance().getItemByCode(itemCode);
    }

    public String createItem(String requestItem)
    {
        JsonObject itemInfo = JsonParser.parseString(requestItem).getAsJsonObject();
        int itemCode = itemInfo.get("itemCode").getAsInt();
        String status = "OK";
        return status;
    }

    public String updateItem(String requestItem)
    {
        JsonObject itemInfo = JsonParser.parseString(requestItem).getAsJsonObject();
        int itemCode = itemInfo.get("itemCode").getAsInt();
        String status = "OK";
        return status;
    }

    public String deleteItem(int itemCode)
    {
        DBAcessService.geInstance().deleteItem(itemCode);
        String status = "OK";
        return status;
    }
}
