package com.smd.customer.db;

import java.util.ArrayList;
import java.util.List;
import com.smd.customer.entity.Customer;
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
