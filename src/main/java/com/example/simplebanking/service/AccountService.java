package com.example.simplebanking.service;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.requireds.CreditRequest;
import com.example.simplebanking.core.utilities.dto.requireds.DebitRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.core.utilities.result.DataResult;
import com.example.simplebanking.core.utilities.result.Result;

import java.util.List;

public interface AccountService {

    DataResult<AccountDto> createAccount(CreateAccountRequest createAccountRequest);

    DataResult<List<AccountDto>>findAll();

    DataResult<AccountDto> getByAccountNumber(String accountNumber);

    DataResult<AccountDto> getById(Long id);

    Result deleteByAccountNumber(String accountNumber);

    DataResult<AccountDto> credit(CreditRequest creditRequest);

    DataResult<AccountDto> debit(DebitRequest debitRequest);

}
