package com.example.simplebanking.core.utilities.dto.requireds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentPhoneBillRequest {
    @NotNull
    @NotBlank
    private String company;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String accountNumber;
    @NotNull
    private double amount;
}
