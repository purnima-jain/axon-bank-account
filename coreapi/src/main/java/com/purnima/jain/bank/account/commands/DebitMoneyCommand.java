package com.purnima.jain.bank.account.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DebitMoneyCommand extends BaseCommand<String> {

	private final double debitAmount;
	private final String currency;

	public DebitMoneyCommand(String id, double debitAmount, String currency) {
		super(id);
		this.debitAmount = debitAmount;
		this.currency = currency;
	}

}
