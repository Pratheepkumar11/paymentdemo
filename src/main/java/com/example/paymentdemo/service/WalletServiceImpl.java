package com.example.paymentdemo.service;


import com.example.paymentdemo.dao.WalletDao;
import com.example.paymentdemo.dao.WalletDaoImpl;
import com.example.paymentdemo.dto.Wallet;
import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;
import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;
import com.example.paymentdemo.exception.passwordexp;

import java.util.Objects;

public class WalletServiceImpl implements WalletService {

	private WalletDao walletRepository = new WalletDaoImpl();
	
	
	public Wallet registerWallet(Wallet newWallet) throws WalletException, InsufficeintAmountException, AlreadyExistingAccountNumberException {
		
		return this.walletRepository.addWallet(newWallet);
		
	}

	public Boolean login(Integer walletId, String password) throws WalletException, passwordexp {
		// TODO Auto-generated method stub
		try {
			boolean success = true;

			Wallet getWallet = walletRepository.getWalletById(walletId);
			if (Objects.equals(getWallet.getPassword(), password)) {
				return success;
			}else {
				throw new passwordexp(super.toString());
			}
		}catch (passwordexp | InsufficeintAmountException ae ){
			throw new passwordexp("password misMatch");

		}


	}

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException, InsufficeintAmountException {
		// TODO Auto-generated method stub
        Double currentAmount,addAmount,finalAmount;
		Wallet currentWallet = this.walletRepository.getWalletById(walletId);

		if(amount>=600) {

			currentAmount = currentWallet.getBalance();
			addAmount = currentAmount + amount;
			currentWallet.setBalance(addAmount);

			//System.out.println(currentWallet);
			this.walletRepository.updateWallet(currentWallet);

			Wallet updatedWallet = this.walletRepository.getWalletById(walletId);
			finalAmount = updatedWallet.getBalance();

		return finalAmount;
	}else {
		throw new InsufficeintAmountException("Invalid Amount");
	}
	}

	public Double showWalletBalance(Integer walletId) throws WalletException, InsufficeintAmountException {
		// TODO Auto-generated method stub

		Wallet showWallet =walletRepository.getWalletById(walletId);
		Double fetchedBalance=showWallet.getBalance();

		return fetchedBalance;
	}

	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException, InsufficeintAmountException {
		// TODO Auto-generated method stub
		boolean transferSuccess = true;
		Double senderBalance, acceptorBalance;
		Wallet senderWallet= walletRepository.getWalletById(fromId);
		Wallet acceptorWallet =walletRepository.getWalletById(toId);
         senderBalance=senderWallet.getBalance();
		System.out.println(senderBalance);
		if(senderBalance>=amount) {
			senderBalance =senderBalance - amount;
			senderWallet.setBalance(senderBalance);
			System.out.println(senderBalance);
			this.walletRepository.updateWallet(senderWallet);
			acceptorBalance = amount + acceptorWallet.getBalance();
			acceptorWallet.setBalance(acceptorBalance);
			this.walletRepository.updateWallet(acceptorWallet);
			return transferSuccess;
		}else {
			throw new InsufficeintAmountException("Insufficient Balance");
		}





	}

	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException, InsufficeintAmountException, passwordexp {
		// TODO Auto-generated method stub
		try {
		Wallet walletForDeletion =walletRepository.getWalletById(walletId);
		if(Objects.equals(walletForDeletion.getPassword(), password)){
			this.walletRepository.deleteWalletById(walletForDeletion.getId());
			return null ;

		}else {
			throw new passwordexp(super.toString());
		}
		}catch (passwordexp | InsufficeintAmountException ae ) {
			throw new passwordexp("password misMatch");
		}


	}

	@Override
	public Double withdraw(Integer walletId, Double amount) throws WalletException, InsufficeintAmountException {


		Double currentBalance,updateBalance,finalBalance;
		Wallet currentWallet = this.walletRepository.getWalletById(walletId);

		currentBalance=currentWallet.getBalance();
		if(currentBalance >=  amount) {
			updateBalance = currentBalance - amount;
			currentWallet.setBalance(updateBalance);

			//System.out.println(currentWallet);
			this.walletRepository.updateWallet(currentWallet);

			Wallet d = this.walletRepository.getWalletById(walletId);
			finalBalance = d.getBalance();
			return finalBalance;
		}else {
			 throw new InsufficeintAmountException("Insufficient Balance");
		}






	}

}
