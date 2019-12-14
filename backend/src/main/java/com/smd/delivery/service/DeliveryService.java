package com.smd.delivery.service;

import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryList;
import com.smd.delivery.entity.Item;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DeliveryService
{
    public DeliveryList getDeliveries()
    {
        DeliveryList deliveryList = new DeliveryList();
        Delivery delivery = new Delivery();
        delivery.setCustomer("Robson Zacarias");
        Item item = new Item();
        item.setItemId(1);
        item.setItemCode(1);
        item.setBrand("Nestlé");
        item.setName("Coffee");
        item.setPrice(2.30);
        item.setWeight(0);
        delivery.setDeliveryId(1);
        delivery.addItem(item);

        BigDecimal bd = BigDecimal.valueOf(delivery.getItems().stream().reduce(0.0, (partialResult, i) -> partialResult + i.getPrice(), Double::sum));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        delivery.setTotalPrice(bd.doubleValue());

        Delivery delivery2 = new Delivery();
        delivery2.setCustomer("Rodolfo Zacarias");
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemCode(1);
        item2.setBrand("Nestlé");
        item2.setName("Coffee");
        item2.setPrice(2.30);
        item2.setWeight(0);
        Item item3 = new Item();
        item3.setItemId(3);
        item3.setItemCode(2);
        item3.setBrand("Rayovac");
        item3.setName("Pilha AAA");
        item3.setPrice(4.60);
        item3.setWeight(0);
        delivery2.setDeliveryId(2);
        delivery2.addItem(item2);
        delivery2.addItem(item3);

        bd = BigDecimal.valueOf(delivery2.getItems().stream().reduce(0.0, (partialResult, i) -> partialResult + i.getPrice(), Double::sum));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        delivery2.setTotalPrice(bd.doubleValue());

        deliveryList.addDelivery(delivery);
        deliveryList.addDelivery(delivery2);

        return deliveryList;
    }
}
