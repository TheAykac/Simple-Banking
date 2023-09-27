package com.example.simplebanking.entities;

import com.example.simplebanking.core.utilities.enums.TransactionTypes;
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
