package com.smd.delivery.entity;

import java.util.ArrayList;
import java.util.List;

public class Delivery
{
    private int deliveryId;

    private double totalPrice;

    private List<Item> itemsList;

    private String customer;

    public Delivery()
    {
        itemsList = new ArrayList<Item>();
    }

    public int getDeliveryId()
    {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId)
    {
        this.deliveryId = deliveryId;
    }

    public List<Item> getItems()
    {
        return itemsList;
    }

    public void setItems(List<Item> itemsList)
    {
        this.itemsList = itemsList;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public void addItem(Item item)
    {
        this.itemsList.add(item);
    }

    public String getCustomer()
    {
        return customer;
    }

    public void setCustomer(String customer)
    {
        this.customer = customer;
    }
}
