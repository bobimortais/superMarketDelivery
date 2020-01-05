package com.smd.delivery.entity;

import javax.persistence.*;

@Entity(name = "item")
@Table
public class Item
{
    @Id
    @GeneratedValue
    @Column(name = "item_code")
    private int itemCode;

    @Column(name = "item_name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    public int getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(int itemCode)
    {
        this.itemCode = itemCode;
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

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
