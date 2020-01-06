package com.smd.delivery.db;

import java.util.ArrayList;
import java.util.List;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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

	public List<DeliveryItem> getItemByDelivery(int deliveryId)
	{
		Transaction tx = null;
		List<DeliveryItem> deliveryItems = new ArrayList<DeliveryItem>();
		Session session = getHibernateSession();

		try
		{
			tx = session.beginTransaction();
			deliveryItems = session.createQuery("FROM delivery_item where delivery_id = " + deliveryId).list();
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

		return deliveryItems;
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

	public int addItemToDelivery(int itemCode, int deliveryId)
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
		return deliveryItem.getDeliveryId();
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
}
