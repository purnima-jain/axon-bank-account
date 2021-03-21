package com.purnima.jain.bank.account.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoneyDebitedEvent extends BaseEvent<String> {

	private final double debitAmount;
	private final String currency;

	public MoneyDebitedEvent(String id, double debitAmount, String currency) {
		super(id);
		this.debitAmount = debitAmount;
		this.currency = currency;
	}

}
