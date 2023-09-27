package com.example.simplebanking.core.utilities.maping;

import com.example.simplebanking.core.utilities.dto.requireds.CreateAccountRequest;
import com.example.simplebanking.core.utilities.dto.responses.AccountDto;
import com.example.simplebanking.entities.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account createAccountRequestToAccount(CreateAccountRequest createAccountRequest);

    AccountDto accountToAccountDto(Account account);

    List<AccountDto> accountsToAccountDtos(List<Account> accounts);

}
