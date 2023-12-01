package com.assignment.dsystem.repository;


import com.assignment.dsystem.model.Customer;
import com.assignment.dsystem.model.Deposit;
import com.assignment.dsystem.model.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    private SessionFactory sessionFactory;

    private Session openSession() {
        if(sessionFactory == null) {
            Configuration configuration;
            configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Deposit.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Loan.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory.openSession();
    }
    public Customer insertCustomer(Customer customer) {
        Session session = openSession();
        session.getTransaction().begin();
        session.persist(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }


    public Customer deleteCustomer(Long id) {
        Session session = openSession();
        Customer customer = (Customer) session.get(Customer.class , id);
        session.getTransaction().begin();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        Session session = openSession();
        session.getTransaction().begin();
        session.merge(customer);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public Customer getCustomer(Long id) {
        Session session = openSession();

        Customer customer = (Customer) session.get(Customer.class , id);
        session.close();
        return customer;
    }

    public List<Customer> getAll(){
        Session session = openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = criteriaBuilder.createQuery(Customer.class);
        criteria.from(Customer.class);
        List<Customer> customers = session.createQuery(criteria).getResultList();
        session.close();
        return customers;
    }

}
