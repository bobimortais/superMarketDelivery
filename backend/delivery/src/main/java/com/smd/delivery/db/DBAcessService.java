package com.smd.delivery.db;

import java.util.ArrayList;
import java.util.List;
import com.smd.delivery.entity.Customer;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryItem;
import com.smd.delivery.entity.Item;
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
	
	public List<Delivery> getAllDeliveries()
	{
		Transaction tx = null;
		List<Delivery> deliveryList = new ArrayList<Delivery>();
		Session session = getHibernateSession();
		
		try 
		{
			tx = session.beginTransaction();
			deliveryList = session.createQuery("FROM delivery").list();
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
		
		return deliveryList;
	}

	public Delivery getDeliveryById(int deliveryId)
	{
		Session session = getHibernateSession();
		Delivery delivery = null;

		try
		{
			delivery = (Delivery) session.get(Delivery.class, deliveryId);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return delivery;
	}

	public int createDelivery(int customerID)
	{
		Transaction tx = null;
		Session session = getHibernateSession();
		Delivery delivery = new Delivery();

		try
		{
			tx = session.beginTransaction();
			delivery.setStatus("o");
			delivery.setCustomerId(customerID);
			session.save(delivery);
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
		return delivery.getDeliveryId();
	}

    public String updateDelivery(int deliveryId, int customerId, String deliveryStatus)
    {
        Transaction tx = null;
        Session session = getHibernateSession();

        try
        {
            tx = session.beginTransaction();
            Delivery delivery = (Delivery) session.get(Delivery.class, deliveryId);

            if(delivery != null)
            {
                delivery.setStatus(deliveryStatus);
                delivery.setCustomerId(customerId);
                session.update(delivery);
                tx.commit();
            }
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

	public String deleteDelivery(int deliveryId)
	{
		Transaction tx = null;
		Session session = getHibernateSession();
		Delivery delivery = new Delivery();

		try
		{
			tx = session.beginTransaction();
			delivery.setDeliveryId(deliveryId);
			session.delete(delivery);
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

	public DeliveryItem addItemToDelivery(int itemCode, int deliveryId)
	{
		Transaction tx = null;
		Session session = getHibernateSession();
		DeliveryItem deliveryItem = new DeliveryItem();

		try
		{
			tx = session.beginTransaction();
			deliveryItem.setItemCode(itemCode);
			deliveryItem.setDeliveryId(deliveryId);
			session.save(deliveryItem);
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
		return deliveryItem;
	}

	public String removeItemFromDelivery(int itemId)
	{
		Transaction tx = null;
		Session session = getHibernateSession();
		DeliveryItem deliveryItem = new DeliveryItem();

		try
		{
			tx = session.beginTransaction();
			deliveryItem.setItemId(itemId);
			session.delete(deliveryItem);
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

    public String removeItemsFromDelivery(List<Integer> idsToDelete)
    {
        Transaction tx = null;
        Session session = getHibernateSession();

        try
        {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM delivery_item item WHERE item.itemId IN :ids");
            query.setParameterList("ids", idsToDelete);
            query.executeUpdate();
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
}
