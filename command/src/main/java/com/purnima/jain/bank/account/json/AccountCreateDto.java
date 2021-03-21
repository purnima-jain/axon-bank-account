package com.purnima.jain.bank.account.json;

import lombok.Data;

@Data
public class AccountCreateDto {

	private double startingBalance;
	private String currency;

}
