package com.assignment.dsystem.service;

import com.assignment.dsystem.model.Deposit;
import com.assignment.dsystem.model.Loan;
import com.assignment.dsystem.repository.CustomerRepository;
import com.assignment.dsystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

;

public class LoanService {
    CustomerRepository customerRepository;
    LoanRepository loanRepository;

    public LoanService(){
        customerRepository = new CustomerRepository();
        loanRepository = new LoanRepository();
    }
    public List<Loan> getAllLoans() {
        List<Loan> loans = loanRepository.getAll();

        if (loans.size() > 0) {
            System.out.println("Get All Loans!");
            return loans;
        } else {
            return Collections.emptyList();
        }
    }


    public Loan getLoanById(Long id){
        Loan loan = loanRepository.getLoan(id);

        if (loan != null) {
            System.out.println("Get Loan! " + loan.toString());
            return loan;
        } else {
            return null;
        }
    }

    public Loan createOrUpdateLoan(Loan entity){
        Loan loan = null;
        try{
            loan = loanRepository.getByCustomerId(entity.getCustomerId());
            if (loan!=null) {
                loan.setAmount(entity.getAmount());
                loan.setDescription(entity.getDescription());
                loan.setDeposits(entity.getDeposits());
                loan.setCustomerId(entity.getCustomerId());
                loan = loanRepository.updateLoan(loan);
                System.out.println("Loan Updated");
                return loan;
            }
        }
        catch (NoResultException e){
            if(customerRepository.getCustomer(entity.getCustomerId())!=null){
                loan = loanRepository.insertLoan(entity);
                System.out.println("Loan Created!");
                return entity;
            }else{
                System.out.println("No customer record exist for the loan");
            }
        }
        return loan;
    }

    public Loan deleteLoanById(Long id) {
        Loan l = loanRepository.deleteLoan(id);
        if (l!=null) {
            System.out.println("Loan Deleted!");
        } else {
            System.out.println("No loan record exist for given id");
        }
        return l;
    }

    public Deposit deposit(Deposit deposit) {
        Loan loan = loanRepository.getLoan(deposit.getLoanId());
        if (loan!=null) {
            loan.getDeposits().add(deposit);
            System.out.println("Deposit has been made " + deposit.toString());
            loanRepository.updateLoan(loan);
            return deposit;
        } else {
            System.out.println("No loan record exist for given deposit");
            return null;
        }
    }


    public Loan deleteDeposit(Deposit deposit) {
        Loan loan = loanRepository.getLoan(deposit.getLoanId());
        if (loan!=null) {
            List<Deposit> list = loan.getDeposits();
            list = list.stream().filter(d -> !Objects.equals(d.getId(), deposit.getId())).collect(Collectors.toList());
            loan.setDeposits(list);
            loanRepository.insertLoan(loan);
            System.out.println("Deposit deleted!");
            return loan;
        } else {
            System.out.println("No loan record exist for given deposit");
            return null;
        }
    }
}
