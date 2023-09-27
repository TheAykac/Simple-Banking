package com.example.simplebanking;

import com.example.simplebanking.core.utilities.result.ErrorDataResult;
import com.example.simplebanking.entities.Account;
import com.example.simplebanking.entities.DepositTransaction;
import com.example.simplebanking.entities.PhoneBillPaymentTransaction;
import com.example.simplebanking.entities.WithdrawalTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class SimpleBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBankingApplication.class, args);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());

        }

        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "VALIDATION.EXCEPTIONS");
        return errorDataResult;

    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessExceptions(Exception e) {
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(e.getMessage(), "BUSINESS.EXCEPTION");
        return errorDataResult;
    }



}
