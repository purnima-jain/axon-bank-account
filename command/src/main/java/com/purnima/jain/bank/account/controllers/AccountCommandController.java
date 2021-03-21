package com.purnima.jain.bank.account.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.bank.account.json.AccountCreateDto;
import com.purnima.jain.bank.account.json.MoneyCreditDto;
import com.purnima.jain.bank.account.json.MoneyDebitDto;
import com.purnima.jain.bank.account.service.AccountCommandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/bank-accounts")
@Slf4j
public class AccountCommandController {
	
	private final AccountCommandService accountCommandService;

	public AccountCommandController(AccountCommandService accountCommandService) {
		super();
		this.accountCommandService = accountCommandService;
	}
	
	@PostMapping
	public CompletableFuture<String> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
		log.info("Inside the AccountCommandController REST Controller for POST::createAccount()..........");
		return accountCommandService.createAccount(accountCreateDto);
	}
	
	@PutMapping(value = "/credits/{accountNumber}")
	public CompletableFuture<String> creditMoneyToAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody MoneyCreditDto moneyCreditDto) {
		log.info("Inside the AccountCommandController REST Controller for PUT::creditMoneyToAccount()..........");
		return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDto);
	}
	
	@PutMapping(value = "/debits/{accountNumber}")
	public CompletableFuture<String> debitMoneyToAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody MoneyDebitDto moneyDebitDto) {
		log.info("Inside the AccountCommandController REST Controller for PUT::debitMoneyToAccount()..........");
		return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDto);
	}

}
