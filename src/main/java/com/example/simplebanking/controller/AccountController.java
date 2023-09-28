package com.example.simplebanking.controller;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.requireds.CreditRequest;
import com.example.simplebanking.core.utilities.dto.requireds.DebitRequest;
import com.example.simplebanking.core.utilities.dto.requireds.PaymentPhoneBillRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.core.utilities.result.DataResult;
import com.example.simplebanking.core.utilities.result.Result;
import com.example.simplebanking.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping("/createAccount")
    public DataResult<AccountDto> createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest){
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping("/findAll")
    public DataResult<List<AccountDto>>findAll(){
        return accountService.findAll();
    }

    @GetMapping("/getByAccountNumber/{accountNumber}")
    public DataResult<AccountDto> getByAccountNumber(@PathVariable String accountNumber){
        return accountService.getByAccountNumber(accountNumber);
    }

    @GetMapping("/getById/{id}")
    public DataResult<AccountDto> getById(@PathVariable Long id){
        return accountService.getById(id);
    }

    @DeleteMapping("deleteByAccountNumber/{accountNumber}")
    public Result deleteByAccountNumber(@PathVariable String accountNumber){
       return accountService.deleteByAccountNumber(accountNumber);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        return accountService.deleteById(id);
    }

    @PostMapping("/credit")
    public DataResult<AccountDto> credit(@RequestBody @Valid CreditRequest creditRequest){
        return accountService.credit(creditRequest);
    }

    @PostMapping("/debit")
    public DataResult<AccountDto> debit(@RequestBody @Valid DebitRequest debitRequest){
        return accountService.debit(debitRequest);
    }

    @PostMapping("/payPhoneBill")
    public Result payPhoneBill(@RequestBody PaymentPhoneBillRequest paymentPhoneBillRequest) {
        return accountService.payPhoneBill(paymentPhoneBillRequest);
    }
}
