package com.example.simplebanking.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PaymentTransaction extends Transaction{
    private String company;
    private double amount;

    public PaymentTransaction (String company, double amount){
        super(amount);
        this.company=company;
    }
}
