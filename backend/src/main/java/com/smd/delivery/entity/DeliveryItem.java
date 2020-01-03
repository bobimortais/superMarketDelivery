package com.smd.delivery.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity(name = "delivery_item")
@Table
public class DeliveryItem
{
    @Id
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_code")
    private int itemCode;

    @Formula("(select i.item_name from item i where i.item_code = item_code)")
    private String name;

    @Formula("(select i.brand from item i where i.item_code = item_code)")
    private String brand;

    @Formula("(select i.description from item i where i.item_code = item_code)")
    private String description;

    @Formula("(select i.price from item i where i.item_code = item_code)")
    private double price;

    @Column(name = "delivery_id")
    private int deliveryId;

    private double weight;

    public int getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(int itemCode)
    {
        this.itemCode = itemCode;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }
}
