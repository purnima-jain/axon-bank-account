package com.purnima.jain.bank.account.service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Service;

import com.purnima.jain.bank.account.domain.CustomBankAccountHistory;
import com.purnima.jain.bank.account.entity.CustomBankAccountHistoryEntity;
import com.purnima.jain.bank.account.events.AccountActivatedEvent;
import com.purnima.jain.bank.account.events.AccountCreatedEvent;
import com.purnima.jain.bank.account.events.AccountHeldEvent;
import com.purnima.jain.bank.account.events.MoneyCreditedEvent;
import com.purnima.jain.bank.account.events.MoneyDebitedEvent;
import com.purnima.jain.bank.account.repo.CustomHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomHistoryService {

	private final CustomHistoryRepository customHistoryRepository;

	private static final String ACCOUNT_OPENING_DESCRIPTION = "New Account Opened with %s in %s as Balance";
	private static final String ACCOUNT_ACTIVATED_DESCRIPTION = "Account has been moved to ACTIVATED status";
	private static final String ACCOUNT_HELD_DESCRIPTION = "Account has been moved to HELD status";
	private static final String ACCOUNT_CREDITED_DESCRIPTION = "Account has been credited with %s in %s";
	private static final String ACCOUNT_DEBITED_DESCRIPTION = "Account has been debited with %s in %s";

	public CustomHistoryService(CustomHistoryRepository customHistoryRepository) {
		super();
		this.customHistoryRepository = customHistoryRepository;
	}

	public List<CustomBankAccountHistory> getCustomHistoryForAccount(String accountNumber) throws InterruptedException, ExecutionException {
		log.info("Inside the CustomHistoryService::getCustomHistoryForAccount(), from the Database........");
		List<CustomBankAccountHistoryEntity> customBankAccountHistoryEntityList = customHistoryRepository.findByAccountNumberOrderById(accountNumber);
		List<CustomBankAccountHistory> customBankAccountHistoryList = customBankAccountHistoryEntityList.stream().map(entity -> new CustomBankAccountHistory(entity.getAccountNumber(), entity.getDescription(), entity.getTimestamp())).collect(Collectors.toList());
		return customBankAccountHistoryList;
	}

	@EventHandler
	public void on(AccountCreatedEvent accountCreatedEvent, @Timestamp Instant timestamp) {
		log.info("Inside the CustomHistoryService::event-handler for AccountCreatedEvent, persisting custom-history to the database........");
		CustomBankAccountHistoryEntity customBankAccountHistoryEntity = new CustomBankAccountHistoryEntity();
		customBankAccountHistoryEntity.setAccountNumber(accountCreatedEvent.getId());
		customBankAccountHistoryEntity.setDescription(String.format(ACCOUNT_OPENING_DESCRIPTION, accountCreatedEvent.getAccountBalance(), accountCreatedEvent.getCurrency()));
		customBankAccountHistoryEntity.setTimestamp(timestamp);

		customHistoryRepository.save(customBankAccountHistoryEntity);
	}

	@EventHandler
	public void on(AccountActivatedEvent accountActivatedEvent, @Timestamp Instant timestamp) {
		log.info("Inside the CustomHistoryService::event-handler for AccountActivatedEvent, persisting custom-history to the database........");
		CustomBankAccountHistoryEntity customBankAccountHistoryEntity = new CustomBankAccountHistoryEntity();
		customBankAccountHistoryEntity.setAccountNumber(accountActivatedEvent.getId());
		customBankAccountHistoryEntity.setDescription(ACCOUNT_ACTIVATED_DESCRIPTION);
		customBankAccountHistoryEntity.setTimestamp(timestamp);

		customHistoryRepository.save(customBankAccountHistoryEntity);
	}

	@EventHandler
	public void on(AccountHeldEvent accountHeldEvent, @Timestamp Instant timestamp) {
		log.info("Inside the CustomHistoryService::event-handler for AccountHeldEvent, persisting custom-history to the database........");
		CustomBankAccountHistoryEntity customBankAccountHistoryEntity = new CustomBankAccountHistoryEntity();
		customBankAccountHistoryEntity.setAccountNumber(accountHeldEvent.getId());
		customBankAccountHistoryEntity.setDescription(ACCOUNT_HELD_DESCRIPTION);
		customBankAccountHistoryEntity.setTimestamp(timestamp);

		customHistoryRepository.save(customBankAccountHistoryEntity);
	}

	@EventHandler
	public void on(MoneyCreditedEvent moneyCreditedEvent, @Timestamp Instant timestamp) {
		log.info("Inside the CustomHistoryService::event-handler for MoneyCreditedEvent, persisting custom-history to the database........");
		CustomBankAccountHistoryEntity customBankAccountHistoryEntity = new CustomBankAccountHistoryEntity();
		customBankAccountHistoryEntity.setAccountNumber(moneyCreditedEvent.getId());
		customBankAccountHistoryEntity.setDescription(String.format(ACCOUNT_CREDITED_DESCRIPTION, moneyCreditedEvent.getCreditAmount(), moneyCreditedEvent.getCurrency()));
		customBankAccountHistoryEntity.setTimestamp(timestamp);

		customHistoryRepository.save(customBankAccountHistoryEntity);
	}

	@EventHandler
	public void on(MoneyDebitedEvent moneyDebitedEvent, @Timestamp Instant timestamp) {
		log.info("Inside the CustomHistoryService::event-handler for MoneyDebitedEvent, persisting custom-history to the database........");
		CustomBankAccountHistoryEntity customBankAccountHistoryEntity = new CustomBankAccountHistoryEntity();
		customBankAccountHistoryEntity.setAccountNumber(moneyDebitedEvent.getId());
		customBankAccountHistoryEntity.setDescription(String.format(ACCOUNT_DEBITED_DESCRIPTION, moneyDebitedEvent.getDebitAmount(), moneyDebitedEvent.getCurrency()));
		customBankAccountHistoryEntity.setTimestamp(timestamp);

		customHistoryRepository.save(customBankAccountHistoryEntity);
	}

}
