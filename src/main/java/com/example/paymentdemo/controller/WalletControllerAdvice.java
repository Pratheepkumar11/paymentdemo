package com.example.paymentdemo.controller;

import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;
import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;
import com.example.paymentdemo.exception.passwordexp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class WalletControllerAdvice {
    @ExceptionHandler({WalletException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String walletControllerAdvice(WalletException e){
        return e.getMessage();
    }
    @ExceptionHandler({passwordexp.class})
    public String walletControllerAdvice(passwordexp e){
        return e.getMessage();
    }
    @ExceptionHandler({InsufficeintAmountException.class})
    public String walletControllerAdvice(InsufficeintAmountException e){
        return e.getMessage();
    }
    @ExceptionHandler({AlreadyExistingAccountNumberException.class})
    public String walletControllerAdvice(AlreadyExistingAccountNumberException e){
        return e.getMessage();
    }


}
