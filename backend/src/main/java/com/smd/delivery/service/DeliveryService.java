package com.smd.delivery.service;

import com.smd.delivery.db.DBAcessService;
import com.smd.delivery.entity.DeliveryList;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService
{
    public DeliveryList getDeliveries()
    {
        DeliveryList deliveryList = new DeliveryList();
        deliveryList.setDeliveryList(DBAcessService.geInstance().getAllDeliveries());
        return deliveryList;
    }
}
