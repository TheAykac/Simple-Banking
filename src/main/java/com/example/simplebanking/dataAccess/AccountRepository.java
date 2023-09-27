package com.example.simplebanking.dataAccess;

import com.example.simplebanking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
}
