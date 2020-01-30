package com.smd.item.db;

import java.util.ArrayList;
import java.util.List;

import com.smd.item.entity.Item;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBAcessService
{

	private static SessionFactory factory; 
	
	private static DBAcessService instance;
	
	public static DBAcessService geInstance()
	{
		if(instance == null)
		{
			instance =  new DBAcessService();
		}
		
		return instance;
	}
	
	public DBAcessService()
	{
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
		factory = configObj.buildSessionFactory(serviceRegistryObj);
	}
	
	public Session getHibernateSession() 
	{
		try 
		{
			Session session = factory.openSession();
			return session;
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

    public List<Item> getAllItems()
    {
        Transaction tx = null;
        List<Item> itemList = new ArrayList<Item>();
        Session session = getHibernateSession();

        try
        {
            tx = session.beginTransaction();
            itemList = session.createQuery("FROM item").list();
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

        return itemList;
    }

    public Item getItemByCode(int itemCode)
    {
        Session session = getHibernateSession();
        Item item = null;

        try
        {
            item = (Item) session.get(Item.class, itemCode);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return item;
    }

    public String deleteItem(int customerId)
    {
        Transaction tx = null;
        Session session = getHibernateSession();

        try
        {
            tx = session.beginTransaction();
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

        String status = "OK";
        return status;
    }

    public int createItem(String brand, String name, String description, double price)
    {
        Transaction tx = null;
        Session session = getHibernateSession();
        Item item = new Item();

        try
        {
            tx = session.beginTransaction();
            item.setName(name);
            item.setBrand(brand);
            item.setDescription(description);
            item.setPrice(price);
            session.save(item);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return item.getItemCode();
    }

    public int updateItem(int itemCode, String brand, String name, String description, double price)
    {
        Transaction tx = null;
        Session session = getHibernateSession();
        Item item = new Item();

        try
        {
            tx = session.beginTransaction();
            item.setItemCode(itemCode);
            item.setName(name);
            item.setBrand(brand);
            item.setDescription(description);
            item.setPrice(price);
            session.update(item);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return item.getItemCode();
    }
}
