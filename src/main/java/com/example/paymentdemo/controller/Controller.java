package com.example.paymentdemo.controller;


import com.example.paymentdemo.dto.Wallet;
import com.example.paymentdemo.dto.WalletDto;
import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;
import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;
import com.example.paymentdemo.exception.passwordexp;
import com.example.paymentdemo.service.WalletService;
import com.example.paymentdemo.service.WalletServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin (origins="http://localhost:4200/")

public class Controller  {


	WalletService ws = new WalletServiceImpl();

	@GetMapping("Wallet/find/{id}")
	//name in parameter and name in path should match
	public String findStudent(@PathVariable int id) throws WalletException, InsufficeintAmountException {
		Double amount=ws.showWalletBalance(id);
		return amount.toString();
	}

	@PostMapping("Wallet/addFund")
	public Double addFund(@RequestBody Wallet wallet) throws WalletException, InsufficeintAmountException {
		Double updatedBalance=ws.addFundsToWallet(wallet.getId(),wallet.getBalance());

		return updatedBalance;
	}

	@PostMapping("Wallet/register")
	public String WalletRegister(@RequestBody Wallet wallet) throws WalletException, InsufficeintAmountException, AlreadyExistingAccountNumberException {
		ws.registerWallet(wallet);
		return "Wallet Added Successfully";

	}

	@PostMapping("Wallet/login")
	public Boolean login( @Validated @RequestBody Wallet wallet) throws WalletException, InsufficeintAmountException, passwordexp {

		boolean userLoggedIn=ws.login(wallet.getId(),wallet.getPassword());
		return userLoggedIn;

	}

	@PatchMapping("Wallet/fundTransfer")
	public Boolean fundTransfer(@RequestBody WalletDto walletdto) throws WalletException, InsufficeintAmountException {

		boolean transferComplete=ws.fundTransfer(walletdto.getIdone(),walletdto.getIdtwo(),walletdto.getAmount());
		return transferComplete;

	}

	@PostMapping("Wallet/withdraw")
	public Double withdraw(@RequestBody Wallet wallet) throws WalletException, InsufficeintAmountException {

		double withdrawAmount=ws.withdraw(wallet.getId(),wallet.getBalance());
		return withdrawAmount;

	}

	@DeleteMapping("Wallet/unRegisterWallet")
	public String deleteWallet(@RequestBody Wallet wallet) throws WalletException, InsufficeintAmountException, passwordexp {
		Wallet deletedWallet=ws.unRegisterWallet(wallet.getId(),wallet.getPassword());
		return "Wallet Deleted Successfully ";
	}
















}


