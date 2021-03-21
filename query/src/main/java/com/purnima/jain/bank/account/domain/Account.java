package com.purnima.jain.bank.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
	
	private String id;

    private double accountBalance;

    private String currency;

    private String status;

}
