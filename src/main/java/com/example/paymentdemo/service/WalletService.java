package com.example.paymentdemo.service;


import com.example.paymentdemo.dto.Wallet;
import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;
import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;
import com.example.paymentdemo.exception.passwordexp;

public interface WalletService {


	Wallet registerWallet(Wallet newWallet) throws WalletException, InsufficeintAmountException, AlreadyExistingAccountNumberException;
	
	Boolean login(Integer walletId,String password) throws WalletException, InsufficeintAmountException, passwordexp;

	Double addFundsToWallet(Integer walletId, Double amount) throws WalletException, InsufficeintAmountException;

	Double showWalletBalance(Integer walletId) throws WalletException, InsufficeintAmountException;

	Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException, InsufficeintAmountException;
	
	Wallet unRegisterWallet(Integer walletId,String password) throws WalletException, InsufficeintAmountException, passwordexp;

	Double withdraw(Integer walletId, Double amount) throws WalletException, InsufficeintAmountException;
}
