package com.purnima.jain.bank.account.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.purnima.jain.bank.account.commands.CreateAccountCommand;
import com.purnima.jain.bank.account.commands.CreditMoneyCommand;
import com.purnima.jain.bank.account.commands.DebitMoneyCommand;
import com.purnima.jain.bank.account.json.AccountCreateDto;
import com.purnima.jain.bank.account.json.MoneyCreditDto;
import com.purnima.jain.bank.account.json.MoneyDebitDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountCommandService {

	private final CommandGateway commandGateway;

	public AccountCommandService(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	public CompletableFuture<String> createAccount(AccountCreateDto accountCreateDto) {
		log.info("Inside the AccountCommandService::createAccount() sending CreateAccountCommand via CommandGateway........");
		return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDto.getStartingBalance(), accountCreateDto.getCurrency()));
	}

	public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDto moneyCreditDto) {
		log.info("Inside the AccountCommandService::creditMoneyToAccount() sending CreditMoneyCommand via CommandGateway........");
		return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDto.getCreditAmount(), moneyCreditDto.getCurrency()));
	}

	public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDto moneyDebitDto) {
		log.info("Inside the AccountCommandService::debitMoneyFromAccount() sending DebitMoneyCommand via CommandGateway........");
		return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDto.getDebitAmount(), moneyDebitDto.getCurrency()));
	}

}
