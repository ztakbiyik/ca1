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
public class DepositRepository {

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
    public Deposit insertDeposit(Deposit deposit) {
        Session session = openSession();
        session.getTransaction().begin();
        session.persist(deposit);
        session.getTransaction().commit();
        session.close();
        return deposit;
    }


    public Deposit deleteDeposit(Long id) {
        Session session = openSession();
        Deposit deposit = (Deposit) session.get(Deposit.class , id);
        session.getTransaction().begin();
        session.delete(deposit);
        session.getTransaction().commit();
        session.close();
        return deposit;
    }

    public Deposit updateDeposit(Deposit deposit) {
        Session session = openSession();
        session.getTransaction().begin();
        session.merge(deposit);
        session.getTransaction().commit();
        session.close();
        return deposit;
    }

    public Deposit getDeposit(Long id) {
        Session session = openSession();

        Deposit deposit = (Deposit) session.get(Deposit.class , id);
        session.close();
        return deposit;
    }

    public List<Deposit> getAll(){
        Session session = openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Deposit> criteria = criteriaBuilder.createQuery(Deposit.class);
        criteria.from(Deposit.class);
        List<Deposit> deposits = session.createQuery(criteria).getResultList();
        session.close();
        return deposits;
    }

}
