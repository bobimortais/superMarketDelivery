package com.smd.delivery.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smd.delivery.utils.CustomDeliverySerializer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "delivery")
@Table
public class Delivery
{
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private int deliveryId;

    private double totalPrice;

    @OneToMany(mappedBy = "deliveryId",  fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<DeliveryItem> itemsList;

    @Column(name = "customer_id")
    private int customerId;

    @Formula("(select concat(c.first_name, ' ', c.last_name) from customer c where c.customer_id = customer_id)")
    private String customerName;

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

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customer)
    {
        this.customerId = customer;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<DeliveryItem> getItems()
    {
        return itemsList;
    }

    public void setItems(List<DeliveryItem> itemsList)
    {
        this.itemsList = itemsList;
    }

    public void addItem(DeliveryItem deliveryItem)
    {
        this.itemsList.add(deliveryItem);
    }
}
