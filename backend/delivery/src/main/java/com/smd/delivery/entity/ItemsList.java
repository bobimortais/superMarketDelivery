package com.smd.delivery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ItemsList
{
    @JsonProperty("items")
    private List<Item> itemList;

    public ItemsList()
    {
        itemList = new ArrayList<Item>();
    }
    public List<Item> geItemList()
    {
        return itemList;
    }

    public void setItemList(List<Item> itemList)
    {
        this.itemList = itemList;
    }

    public void addItem(Item item)
    {
        this.itemList.add(item);
    }
}
