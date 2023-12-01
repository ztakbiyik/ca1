package com.assignment.dsystem.service;

import com.assignment.dsystem.model.Customer;
import com.assignment.dsystem.model.Loan;
import com.assignment.dsystem.repository.CustomerRepository;
import com.assignment.dsystem.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    CustomerRepository customerRepository;
    LoanRepository loanRepository;

    public CustomerService() {
        customerRepository = new CustomerRepository();
        loanRepository = new LoanRepository();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.getAll();

        if (!customers.isEmpty()) {

            System.out.println("Get All Customer!");
            return customers;
        } else {
            return new ArrayList<Customer>();
        }
    }


    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.getCustomer(id);

        if (customer != null) {
            return customer;
        } else {
            System.out.println("No customer record exist for given id");
            return null;
        }
    }


    public Loan getCustomerLoanById(Long id) {
        Customer customer = customerRepository.getCustomer(id);
        if (customer != null) {
            Loan l = loanRepository.getByCustomerId(id);
            if (l != null) {
                System.out.println("Loan found for customer " + customer.toString());
                return l;
            } else {
                System.out.println("No loan record exist for given customer id");
                return null;
            }
        } else {
            System.out.println("No customer record exist for given id");
            return null;
        }

    }

    public Customer updateCustomer(Customer entity) {
        System.out.println("updateCustomer");
        return customerRepository.updateCustomer(entity);
    }

    public Customer createCustomer(Customer entity) {
        System.out.println("createCustomer");
        return customerRepository.insertCustomer(entity);
    }

    public Customer deleteCustomerById(Long id) {
        Customer c = customerRepository.deleteCustomer(id);
        if (c != null) {
            System.out.println("Customer Deleted!");
        } else {
            System.out.println("No customer record exist for given id");
        }
        return c;
    }

}
