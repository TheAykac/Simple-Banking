package com.example.simplebanking.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PhoneBillPaymentTransaction extends PaymentTransaction {
    private String phoneNumber;
    public PhoneBillPaymentTransaction(String accountNumber,String company, String phoneNumber, double amount) {
        super(company,amount);
        this.phoneNumber=phoneNumber;
    }
}
