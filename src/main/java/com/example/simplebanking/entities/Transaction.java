package com.example.simplebanking.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "approval_code")
    private String approvalCode;

    @ManyToOne()
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }
}



