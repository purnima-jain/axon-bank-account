package com.purnima.jain.bank.account.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditMoneyCommand extends BaseCommand<String> {

	private final double creditAmount;
	private final String currency;

	public CreditMoneyCommand(String id, double creditAmount, String currency) {
		super(id);
		this.creditAmount = creditAmount;
		this.currency = currency;
	}

}
