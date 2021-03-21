package com.purnima.jain.bank.account.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.bank.account.service.AccountHistoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/bank-accounts")
@Slf4j
public class AccountHistoryController {
	
	private final AccountHistoryService accountHistoryService;

	public AccountHistoryController(AccountHistoryService accountHistoryService) {
		super();
		this.accountHistoryService = accountHistoryService;
	}
	
	@GetMapping("/{accountNumber}/history")
	public List<Object> getHistoryForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		log.info("Inside the AccountHistoryController REST Controller for GET::getHistoryForAccount()..........");
		return accountHistoryService.getHistoryForAccount(accountNumber);
	}

}
