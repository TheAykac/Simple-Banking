package com.example.simplebanking.service;

import com.example.simplebanking.core.utilities.dto.requireds.PaymentPhoneBillRequest;
import com.example.simplebanking.dataAccess.TransactionRepository;
import com.example.simplebanking.entities.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class TransactionServiceImp implements TransactionService{
    private  TransactionRepository transactionRepository;

    private  AccountService accountService;


    public TransactionServiceImp(TransactionRepository transactionRepository,@Lazy AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public void Deposit(Account account, double amount) {
        UUID randomUUID = UUID.randomUUID();
        String  approvalCode = randomUUID.toString();
        Transaction transaction = new DepositTransaction(amount);
        transaction.setApprovalCode(approvalCode);
        transaction.setAccount(account);
        transactionRepository.save(transaction);
    }

    @Override
    public void withdrawal(Account account,double amount) {
        UUID randomUUID = UUID.randomUUID();
        String  approvalCode = randomUUID.toString();
        Transaction transaction = new WithdrawalTransaction(amount);
        transaction.setAccount(account);
        transaction.setApprovalCode(approvalCode);
        transactionRepository.save(transaction);
    }

    @Override
    public void payment(PaymentPhoneBillRequest paymentPhoneBillRequest) {
        UUID randomUUID = UUID.randomUUID();
        String  approvalCode = randomUUID.toString();
        Transaction transaction = new PhoneBillPaymentTransaction(paymentPhoneBillRequest.getAccountNumber(), paymentPhoneBillRequest.getCompany(), paymentPhoneBillRequest.getPhoneNumber(), paymentPhoneBillRequest.getAmount());
        transaction.setApprovalCode(approvalCode);
        Account account = accountService.findByAccountNumber(paymentPhoneBillRequest.getAccountNumber());
        transaction.setAccount(account);
        transactionRepository.save(transaction);
    }
}
