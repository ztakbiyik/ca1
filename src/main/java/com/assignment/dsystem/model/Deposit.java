package com.assignment.dsystem.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DEPOSITS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "deposit_date")
    private Date depositDate;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "loan_id")
    private Long loanId;
    @Column(name = "customer_id")
    private Long customerId;
}
