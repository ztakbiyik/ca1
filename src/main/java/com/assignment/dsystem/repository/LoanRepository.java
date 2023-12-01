package com.assignment.dsystem.repository;


import com.assignment.dsystem.model.Customer;
import com.assignment.dsystem.model.Deposit;
import com.assignment.dsystem.model.Loan;
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
public class LoanRepository {

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
    public Loan insertLoan(Loan loan) {
        Session session = openSession();
        session.getTransaction().begin();
        session.persist(loan);
        session.getTransaction().commit();
        session.close();
        return loan;
    }


    public Loan deleteLoan(Long id) {
        Session session = openSession();
        Loan loan = (Loan) session.get(Loan.class , id);
        session.getTransaction().begin();
        session.delete(loan);
        session.getTransaction().commit();
        session.close();
        return loan;
    }

    public Loan updateLoan(Loan loan) {
        Session session = openSession();
        session.getTransaction().begin();
        session.merge(loan);
        session.getTransaction().commit();
        session.close();
        return loan;
    }

    public Loan getLoan(Long id) {
        Session session = openSession();

        Loan loan = (Loan) session.get(Loan.class , id);
        session.close();
        return loan;
    }

    public List<Loan> getAll(){
        Session session = openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Loan> criteria = criteriaBuilder.createQuery(Loan.class);
        criteria.from(Loan.class);
        List<Loan> companies = session.createQuery(criteria).getResultList();
        session.close();
        return companies;
    }

    public Loan getByCustomerId(Long id) {
        Session session = openSession();
        return session.createQuery("from Loan s where s.customerId = :customerId", Loan.class)
                .setParameter("customerId", id).getSingleResult();
    }
}
