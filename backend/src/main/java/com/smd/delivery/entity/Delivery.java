package com.smd.delivery.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smd.delivery.utils.CustomDeliverySerializer;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "delivery")
@Table
public class Delivery
{
    @Id
    @Column(name = "delivery_id")
    private int deliveryId;

    private double totalPrice;

   @OneToMany(mappedBy = "deliveryId",  fetch = FetchType.EAGER)
    private List<DeliveryItem> itemsList;

    @Column(name = "customer_id")
    private String customer;

    @Column(name = "status")
    @JsonSerialize(using = CustomDeliverySerializer.class)
    private String status;

    public Delivery()
    {
        itemsList = new ArrayList<DeliveryItem>();
    }

    public int getDeliveryId()
    {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId)
    {
        this.deliveryId = deliveryId;
    }

    public List<DeliveryItem> getItems()
    {
        return itemsList;
    }

    public void setItems(List<DeliveryItem> itemsList)
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

    public void addItem(DeliveryItem deliveryItem)
    {
        this.itemsList.add(deliveryItem);
    }

    public String getCustomer()
    {
        return customer;
    }

    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
