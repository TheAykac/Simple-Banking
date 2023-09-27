package com.example.simplebanking.entities;

import com.example.simplebanking.core.utilities.exception.BusinessException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "account_nyumber")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account (String owner,double balance){
        this.owner=owner;
        this.balance=balance;
        this.transactions = new ArrayList<>();
    }


    public void post(Transaction transaction) throws BusinessException {
        if(transaction instanceof DepositTransaction) {
            this.credit(transaction.getAmount());
        } else if(transaction instanceof WithdrawalTransaction) {
            this.debit(transaction.getAmount());
        }
        transaction.setAccount(this);
    }

    public void credit(double amount) {
        this.balance += amount;
        transactions.add(new DepositTransaction(amount));
    }

    public void debit(double amount) throws BusinessException {
        if(this.balance < amount) {
            throw new BusinessException("Yetersiz Bakiye");
        }else{
            this.balance -= amount;
            transactions.add(new WithdrawalTransaction(amount));
        }
    }
}
