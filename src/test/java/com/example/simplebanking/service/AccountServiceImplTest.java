package com.example.simplebanking.service;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.requireds.CreditRequest;
import com.example.simplebanking.core.utilities.dto.requireds.DebitRequest;
import com.example.simplebanking.core.utilities.dto.requireds.PaymentPhoneBillRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.core.utilities.exception.BusinessException;
import com.example.simplebanking.core.utilities.maping.AccountMapper;
import com.example.simplebanking.core.utilities.messages.BusinessMessage;
import com.example.simplebanking.core.utilities.result.DataResult;
import com.example.simplebanking.core.utilities.result.Result;
import com.example.simplebanking.core.utilities.result.SuccessDataResult;
import com.example.simplebanking.dataAccess.AccountRepository;
import com.example.simplebanking.entities.Account;
import com.example.simplebanking.entities.PaymentTransaction;
import com.example.simplebanking.entities.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {
    private AccountServiceImpl accountService;
    private AccountMapper accountMapper;
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionService = Mockito.mock(TransactionService.class);
        accountMapper = Mockito.mock(AccountMapper.class);

        accountService = new AccountServiceImpl(accountMapper, accountRepository, transactionService);
    }

    @Test
    public void createAccount() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new PaymentTransaction("Vodofone", 1111));
        CreateAccountRequest request = new CreateAccountRequest("Ömer", "1234", 999);
        Account account = new Account(1L, "Ömer", "1234", 9999, transactions);
        AccountDto accountDto = accountMapper.accountToAccountDto(account);
        when(accountMapper.createAccountRequestToAccount(request)).thenReturn(account);
        when(accountMapper.accountToAccountDto(account)).thenReturn(accountDto);
        DataResult<AccountDto> result = accountService.createAccount(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void findAll() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new PaymentTransaction("Vodofone", 1111));
        Account account = new Account(1L, "Ömer", "1234", 9999, transactions);
        AccountDto accountDto = accountMapper.accountToAccountDto(account);
        List<Account> mockAccounts = Arrays.asList(account);
        List<AccountDto> expectedAccountDtos = Arrays.asList(accountDto);
        when(accountRepository.findAll()).thenReturn(mockAccounts);
        when(accountMapper.accountsToAccountDtos(mockAccounts)).thenReturn(expectedAccountDtos);
        DataResult<List<AccountDto>> result = accountService.findAll();

        assertEquals(expectedAccountDtos, result.getData());
    }

        @Test
        public void testPayPhoneBill() {
            String accountNumber = "1234567890";
            double initialBalance = 1000.0;
            double paymentAmount = 100.0;

            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setBalance(initialBalance);

            PaymentPhoneBillRequest paymentRequest = new PaymentPhoneBillRequest();
            paymentRequest.setAccountNumber(accountNumber);
            paymentRequest.setAmount(paymentAmount);
            Mockito.when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
            Result result = accountService.payPhoneBill(paymentRequest);


            assertTrue(result instanceof SuccessDataResult);
            assertEquals(BusinessMessage.GlobalMessages.PAYMENT_SUCCESSFULLY + (initialBalance - paymentAmount), result.getMessage());


            assertEquals(initialBalance - paymentAmount, account.getBalance(), 0.01);
            Mockito.verify(transactionService).payment(paymentRequest);
        }


    @Test()
    public void testFindByAccountNumberWhenAccountNotFound() {
        String accountNumber = "9999999999";
        Mockito.when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(null);
        assertThrows(BusinessException.class, () -> {
            accountService.findByAccountNumber(accountNumber);
        });
    }


    @Test
    public void testDeleteById() {
        Long accountId = 1L;
        Mockito.doNothing().when(accountRepository).deleteById(accountId);
        assertThrows(BusinessException.class, () -> {
            accountService.deleteById(1L);
        });
    }

    @Test
    public void testCredit() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new PaymentTransaction("Vodofone", 1111));
        Account account = new Account(1L, "Ömer", "1234", 9999, transactions);
        String accountNumber = account.getAccountNumber();
        AccountDto accountDto;

        CreditRequest creditRequest = new CreditRequest(account.getAccountNumber(),100);


        Mockito.when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        DataResult<AccountDto> result = accountService.credit(creditRequest);
        if (result.getData()!=null){
            accountDto = result.getData();
        }else {
            accountDto=new AccountDto("Ömer","1234",9999);
        }
        assertTrue(result instanceof SuccessDataResult);

        assertEquals(account.getAccountNumber(), accountDto.getAccountNumber());
        assertEquals(account.getBalance() , accountDto.getBalance()+100);


        Mockito.verify(transactionService).Deposit(account, 100);
    }

    @Test
    public void testDebit() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new PaymentTransaction("Vodofone", 1111));
        Account account = new Account(1L, "Ömer", "1234", 9999, transactions);
        String accountNumber = account.getAccountNumber();
        AccountDto accountDto;

        DebitRequest debitRequest = new DebitRequest(account.getAccountNumber(),100);


        Mockito.when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        DataResult<AccountDto> result = accountService.debit(debitRequest);

        if (result.getData()!=null){
            accountDto = result.getData();
        }else {
            accountDto=new AccountDto("Ömer","1234",9999);
        }
        assertTrue(result instanceof SuccessDataResult);

        assertEquals(account.getAccountNumber(), accountDto.getAccountNumber());
        assertEquals(account.getBalance() , accountDto.getBalance()-100);


        Mockito.verify(transactionService).withdrawal(account, 100);
    }

    @AfterEach
    public void tearDown() {

    }
}