package com.example.simplebanking.core.utilities.dto.requireds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountRequest {
    @NotNull(message = "Hesap Sahibi Alanı Boş Bırakılamaz")
    @NotBlank(message = "Hesap Sahibi Alanı Boş Bırakılamaz")
    private String owner;
    @NotNull(message = "Hesap Numarası Boş Bırakılamaz")
    @NotBlank(message = "Hesap Numarası Boş Bırakılamaz")
    private String accountNumber;
    @NotNull(message = "Hesap Numarası Boş Bırakılamaz")
    private double balance;
}
