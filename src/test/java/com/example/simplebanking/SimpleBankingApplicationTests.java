package com.example.simplebanking;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.core.utilities.maping.AccountMapper;
import com.example.simplebanking.core.utilities.result.DataResult;
import com.example.simplebanking.core.utilities.result.Result;
import com.example.simplebanking.dataAccess.AccountRepository;
import com.example.simplebanking.dataAccess.TransactionRepository;
import com.example.simplebanking.entities.*;
import com.example.simplebanking.service.AccountService;
import com.example.simplebanking.service.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SimpleBankingApplicationTests {


	@Mock
	private AccountMapper accountMapper;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private AccountServiceImpl accountService;

	@Before("")
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateAccount() {
		List<Transaction> transactions =new ArrayList<>();
		transactions.add(new PaymentTransaction("Vodofone",1111));
		CreateAccountRequest request = new CreateAccountRequest("Ömer","1234",999);
		Account account = new Account(1L,"Ömer","1234",9999,transactions);
		AccountDto accountDto = accountMapper.accountToAccountDto(account);
		when(accountMapper.createAccountRequestToAccount(request)).thenReturn(account);
		when(accountMapper.accountToAccountDto(account)).thenReturn(accountDto);

		DataResult<AccountDto> result = accountService.createAccount(request);
		assertTrue(result.isSuccess());
	}

	public void findByAccountNumber(){
		List<Transaction> transactions =new ArrayList<>();
		transactions.add(new PaymentTransaction("Vodofone",1111));
		CreateAccountRequest request = new CreateAccountRequest("Ömer","1234",999);

	}

}



