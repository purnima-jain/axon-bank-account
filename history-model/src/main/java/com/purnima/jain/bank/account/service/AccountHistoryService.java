package com.purnima.jain.bank.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountHistoryService {
	
	private final EventStore eventStore;	

	public AccountHistoryService(EventStore eventStore) {
		super();
		this.eventStore = eventStore;
	}

	public List<Object> getHistoryForAccount(String accountNumber) {
		log.info("Inside the AccountHistoryService::getHistoryForAccount(), from the EventStore........");
		return eventStore.readEvents(accountNumber).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

}
