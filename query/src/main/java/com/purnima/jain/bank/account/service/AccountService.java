package com.purnima.jain.bank.account.service;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.purnima.jain.bank.account.domain.Account;
import com.purnima.jain.bank.account.entity.AccountEntity;
import com.purnima.jain.bank.account.events.AccountActivatedEvent;
import com.purnima.jain.bank.account.events.AccountCreatedEvent;
import com.purnima.jain.bank.account.events.AccountHeldEvent;
import com.purnima.jain.bank.account.events.MoneyCreditedEvent;
import com.purnima.jain.bank.account.events.MoneyDebitedEvent;
import com.purnima.jain.bank.account.repo.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {
	
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	@EventHandler
	public void on(AccountCreatedEvent accountCreatedEvent) {
		log.info("Inside the AccountService::event-handler for AccountCreatedEvent, persisting account to the database........");
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(accountCreatedEvent.getId());
		accountEntity.setAccountBalance(accountCreatedEvent.getAccountBalance());
		accountEntity.setCurrency(accountCreatedEvent.getCurrency());
		accountEntity.setStatus("CREATED");
		
		accountRepository.save(accountEntity);
	}
	
	@EventHandler
	public void on(AccountActivatedEvent accountActivatedEvent) {
		log.info("Inside the AccountService::event-handler for AccountActivatedEvent, updating account in the database........");
		AccountEntity accountEntity = accountRepository.findById(accountActivatedEvent.getId()).orElse(null);
		accountEntity.setStatus("ACTIVATED");
		
		accountRepository.save(accountEntity);
	}
	
	@EventHandler
	public void on(AccountHeldEvent accountHeldEvent) {
		log.info("Inside the AccountService::event-handler for AccountHeldEvent, updating account in the database........");
		AccountEntity accountEntity = accountRepository.findById(accountHeldEvent.getId()).orElse(null);
		accountEntity.setStatus("HELD");
		
		accountRepository.save(accountEntity);
	}
	
	@EventHandler
	public void on(MoneyCreditedEvent moneyCreditedEvent) {
		log.info("Inside the AccountService::event-handler for MoneyCreditedEvent, updating account in the database........");
		AccountEntity accountEntity = accountRepository.findById(moneyCreditedEvent.getId()).orElse(null);
		accountEntity.setAccountBalance(accountEntity.getAccountBalance() + moneyCreditedEvent.getCreditAmount());
		
		accountRepository.save(accountEntity);
	}
	
	@EventHandler
	public void on(MoneyDebitedEvent moneyDebitedEvent) {
		log.info("Inside the AccountService::event-handler for MoneyDebitedEvent, updating account in the database........");
		AccountEntity accountEntity = accountRepository.findById(moneyDebitedEvent.getId()).orElse(null);
		accountEntity.setAccountBalance(accountEntity.getAccountBalance() - moneyDebitedEvent.getDebitAmount());
		
		accountRepository.save(accountEntity);
	}
	
	
	public Account findById(String accountNumber) {
		log.info("Inside the AccountService::findById(), from the database........");
		AccountEntity accountEntity = accountRepository.findById(accountNumber).orElse(null);
		
		Account account = new Account(accountEntity.getId(), accountEntity.getAccountBalance(), accountEntity.getCurrency(), accountEntity.getStatus());
		
		return account;
	}
	
	// TODO: For this you need EventStore configured, so we will do this a little later
//	@Override
//    public List<Object> listEventsForAccount(String accountNumber) {
//        return eventStore.readEvents(accountNumber).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
//    }

}
