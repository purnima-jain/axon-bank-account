package com.purnima.jain.bank.account.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.bank.account.domain.Account;
import com.purnima.jain.bank.account.json.AccountResponseDto;
import com.purnima.jain.bank.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/bank-accounts")
@Slf4j
public class AccountController {
	
	private final AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/{accountNumber}")
	public AccountResponseDto findById(@PathVariable("accountNumber") String accountNumber) {
		log.info("Inside the AccountController REST Controller for GET::findById()..........");
		Account account = accountService.findById(accountNumber);
		return new AccountResponseDto(account);
	}
	
}
