package com.purnima.jain.bank.account.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountCreatedEvent extends BaseEvent<String> {

	private final double accountBalance;
	private final String currency;

	public AccountCreatedEvent(String id, Double accountBalance, String currency) {
		super(id);
		this.accountBalance = accountBalance;
		this.currency = currency;
	}

}
