package com.purnima.jain.bank.account.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAccountCommand extends BaseCommand<String> {

	private final double accountBalance;
	private final String currency;

	public CreateAccountCommand(String id, double accountBalance, String currency) {
		super(id);
		this.accountBalance = accountBalance;
		this.currency = currency;
	}

}
