package com.purnima.jain.bank.account.json;

import com.purnima.jain.bank.account.domain.Account;

import lombok.Data;

@Data
public class AccountResponseDto {

	private final String id;

	private final double accountBalance;

	private final String currency;

	private final String status;

	public AccountResponseDto(Account account) {
		this.id = account.getId();
		this.accountBalance = account.getAccountBalance();
		this.currency = account.getCurrency();
		this.status = account.getStatus();
	}

}
