package com.smd.delivery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DeliveryList
{
    @JsonProperty("deliveries")
    private List<Delivery> deliveryList;

    public DeliveryList()
    {
        deliveryList = new ArrayList<Delivery>();
    }
    public List<Delivery> getDeliveryList()
    {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList)
    {
        this.deliveryList = deliveryList;
    }

    public void addDelivery(Delivery delivery)
    {
        this.deliveryList.add(delivery);
    }
}
