package com.smd.item.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ItemList
{
    @JsonProperty("items")
    private List<Item> itemList;

    public ItemList()
    {
        itemList = new ArrayList<Item>();
    }
    public List<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(List<Item> itemList)
    {
        this.itemList = itemList;
    }

    public void addDelivery(Item item)
    {
        this.itemList.add(item);
    }
}
