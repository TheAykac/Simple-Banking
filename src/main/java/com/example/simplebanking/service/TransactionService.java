package com.example.simplebanking.service;

import com.example.simplebanking.core.utilities.dto.requireds.PaymentPhoneBillRequest;
import com.example.simplebanking.entities.Account;
import com.example.simplebanking.entities.DepositTransaction;
import com.example.simplebanking.entities.Transaction;
import com.example.simplebanking.entities.WithdrawalTransaction;

public interface TransactionService {
    void Deposit (Account account,double amount);
    void withdrawal (Account account,double amount);
    void payment (PaymentPhoneBillRequest paymentPhoneBillRequest);
}
