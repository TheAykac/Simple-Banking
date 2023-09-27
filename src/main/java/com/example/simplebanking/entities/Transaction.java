package com.example.simplebanking.entities;

import com.example.simplebanking.core.utilities.enums.TransactionTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @ManyToOne()
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }


}
