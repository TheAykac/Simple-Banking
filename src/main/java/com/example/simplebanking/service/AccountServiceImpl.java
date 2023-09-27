package com.example.simplebanking.service;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.requireds.CreditRequest;
import com.example.simplebanking.core.utilities.dto.requireds.DebitRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.core.utilities.exception.BusinessException;
import com.example.simplebanking.core.utilities.maping.AccountMapper;
import com.example.simplebanking.core.utilities.messages.BusinessMessage;
import com.example.simplebanking.core.utilities.result.DataResult;
import com.example.simplebanking.core.utilities.result.Result;
import com.example.simplebanking.core.utilities.result.SuccessDataResult;
import com.example.simplebanking.dataAccess.AccountRepository;
import com.example.simplebanking.dataAccess.TransactionRepository;
import com.example.simplebanking.entities.Account;
import com.example.simplebanking.entities.DepositTransaction;
import com.example.simplebanking.entities.Transaction;
import com.example.simplebanking.entities.WithdrawalTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Override
    public DataResult<AccountDto> createAccount(CreateAccountRequest createAccountRequest) {
        Account account = accountMapper.createAccountRequestToAccount(createAccountRequest);
        accountRepository.save(account);
        AccountDto response = accountMapper.accountToAccountDto(account);
        return new SuccessDataResult<>(response,BusinessMessage.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<AccountDto>>findAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = accountMapper.accountsToAccountDtos(accounts);
        return new SuccessDataResult<>(accountDtos,BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY) ;
    }

    @Override
    public DataResult<AccountDto> getByAccountNumber(String accountNumber) {
        checkIfExistsByAccountNumber(accountNumber);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        AccountDto response = accountMapper.accountToAccountDto(account);
        return new SuccessDataResult<>(response,BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<AccountDto> getById(Long id) {
        checkIfExistsById(id);
        Account account = accountRepository.findById(id).get();
        AccountDto response = accountMapper.accountToAccountDto(account);
        return new SuccessDataResult<>(response,BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result deleteByAccountNumber(String accountNumber) {
        checkIfExistsByAccountNumber(accountNumber);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        accountRepository.delete(account);
        return new SuccessDataResult<>(account,BusinessMessage.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<AccountDto> credit(CreditRequest creditRequest) {
        checkIfExistsByAccountNumber(creditRequest.getAccountNumber());
        Account account = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        account.setBalance(account.getBalance() + creditRequest.getCreditAmount());
        accountRepository.save(account);
        Transaction transaction = new DepositTransaction(creditRequest.getCreditAmount());
        transaction.setAccount(account);
        transactionRepository.save(transaction);
        AccountDto response = accountMapper.accountToAccountDto(account);
        return new SuccessDataResult<>(response,BusinessMessage.GlobalMessages.CREDIT_SUCCESSFULLY+account.getBalance());
    }

    @Override
    public DataResult<AccountDto> debit(DebitRequest debitRequest) {
        checkIfExistsByAccountNumber(debitRequest.getAccountNumber());
        checkBalance(debitRequest.getAccountNumber(),debitRequest.getDebitAmount());
        Account account = accountRepository.findByAccountNumber(debitRequest.getAccountNumber());
        account.setBalance(account.getBalance() - debitRequest.getDebitAmount());
        accountRepository.save(account);
        Transaction transaction = new WithdrawalTransaction(debitRequest.getDebitAmount());
        transaction.setAccount(account);
        transactionRepository.save(transaction);
        AccountDto response = accountMapper.accountToAccountDto(account);
        return new SuccessDataResult<>(response, BusinessMessage.GlobalMessages.DEBIT_SUCCESSFULLY+account.getBalance());
    }

    private void checkIfExistsByAccountNumber(String accountNumber) throws BusinessException {
        if (!accountRepository.existsByAccountNumber(accountNumber)) {
            throw new BusinessException("Banka Hesabı Bulunamadı");
        }
    }

    private void checkIfExistsById(Long id) throws BusinessException {
        if (!accountRepository.existsById(id)) {
            throw new BusinessException("Banka Hesabı Bulunamadı");
        }
    }

    private void checkBalance (String accountNumber,double amount) throws BusinessException{
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account.getBalance()<amount) {
            throw new BusinessException("Yetersiz Bakiye");
        }
    }
}
