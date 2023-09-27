package com.example.simplebanking.core.utilities.dto.requireds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditRequest {
    @NotNull(message = "Hesap Numarası Boş Bırakılamaz")
    @NotBlank(message = "Hesap Numarası Boş Bırakılamaz")
    private String accountNumber;
    @NotNull(message = "Tutar Boş Bırakılamaz")
    private double creditAmount;
}
