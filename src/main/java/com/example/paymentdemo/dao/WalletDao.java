package com.example.paymentdemo.dao;


import com.example.paymentdemo.dto.Wallet;
import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;
import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;

public interface WalletDao {
	//CRUD
	Wallet addWallet(Wallet newWallet) throws WalletException, AlreadyExistingAccountNumberException, InsufficeintAmountException;
	Wallet getWalletById(Integer walletId) throws WalletException, InsufficeintAmountException;
	Wallet updateWallet(Wallet updateWallet)throws WalletException;
	Wallet deleteWalletById(Integer walletID)throws WalletException;
}
