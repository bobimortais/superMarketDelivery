package com.smd.delivery.db;

import java.util.ArrayList;
import java.util.List;
import com.smd.delivery.entity.Customer;
import com.smd.delivery.entity.Delivery;
import com.smd.delivery.entity.DeliveryItem;
import com.smd.delivery.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public String removeItemsFromDelivery(int itemId)
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
        Customer customer = new Customer();

        try
        {
            tx = session.beginTransaction();
            customer.setCustomerId(customerId);
            session.delete(customer);
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

    public List<Customer> getAllCustomers()
    {
        Transaction tx = null;
        List<Customer> customerList = new ArrayList<>();
        Session session = getHibernateSession();

        try
        {
            tx = session.beginTransaction();
            customerList = session.createQuery("FROM customer").list();
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

        return customerList;
    }

    public Customer getCustomerById(int customerID)
    {
        Session session = getHibernateSession();
        Customer customer = null;

        try
        {
            customer = (Customer) session.get(Customer.class, customerID);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return customer;
    }

    public int createCustomer(String firstName, String lastName, String cpf, String sex, String phone)
    {
        Transaction tx = null;
        Session session = getHibernateSession();
        Customer customer = new Customer();

        try
        {
            tx = session.beginTransaction();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setCpf(cpf);
            customer.setSex(sex.charAt(0));
            customer.setPhone(phone);
            session.save(customer);
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
        return customer.getCustomerId();
    }

    public int updateCustomer(int customerId, String firstName, String lastName, String cpf, String sex, String phone)
    {
        Transaction tx = null;
        Session session = getHibernateSession();
        Customer customer = new Customer();

        try
        {
            tx = session.beginTransaction();
            customer.setCustomerId(customerId);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setCpf(cpf);
            customer.setSex(sex.charAt(0));
            customer.setPhone(phone);
            session.update(customer);
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
        return customer.getCustomerId();
    }
}
