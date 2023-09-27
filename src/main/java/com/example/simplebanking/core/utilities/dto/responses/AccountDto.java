package com.example.simplebanking.core.utilities.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private String owner;
    private String accountNumber;
    private double balance;
}
