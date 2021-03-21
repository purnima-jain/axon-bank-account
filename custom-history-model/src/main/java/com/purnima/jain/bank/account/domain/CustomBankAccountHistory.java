package com.purnima.jain.bank.account.domain;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomBankAccountHistory {

	private final String accountNumber;
	private final String description;
	private final Instant timestamp;

}
