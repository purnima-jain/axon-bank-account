package com.purnima.jain.bank.account.json;

import lombok.Data;

@Data
public class MoneyDebitDto {

	private double debitAmount;
	private String currency;

}
