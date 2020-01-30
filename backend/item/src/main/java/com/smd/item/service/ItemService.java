package com.smd.item.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smd.item.db.DBAcessService;
import com.smd.item.entity.Item;
import com.smd.item.entity.ItemList;
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

    public int createItem(String requestItem)
    {
        JsonObject itemInfo = JsonParser.parseString(requestItem).getAsJsonObject();
        String brand = itemInfo.get("brand").getAsString();
        String itemName = itemInfo.get("name").getAsString();
        String description = itemInfo.get("description").getAsString();
        double price = itemInfo.get("price").getAsDouble();
        int itemCode = DBAcessService.geInstance().createItem(brand, itemName, description, price);
        return itemCode;
    }

    public int updateItem(String requestItem)
    {
        JsonObject itemInfo = JsonParser.parseString(requestItem).getAsJsonObject();
        int itemCode = itemInfo.get("itemCode").getAsInt();
        String brand = itemInfo.get("brand").getAsString();
        String itemName = itemInfo.get("name").getAsString();
        String description = itemInfo.get("description").getAsString();
        double price = itemInfo.get("price").getAsDouble();
        String status = "OK";
        DBAcessService.geInstance().updateItem(itemCode, brand, itemName, description, price);
        return itemCode;
    }

    public String deleteItem(int itemCode)
    {
        DBAcessService.geInstance().deleteItem(itemCode);
        String status = "OK";
        return status;
    }
}
