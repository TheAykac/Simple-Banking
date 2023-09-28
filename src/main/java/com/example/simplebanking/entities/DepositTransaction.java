package com.example.simplebanking.entities;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super(amount);
    }
}
