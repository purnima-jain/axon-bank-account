package com.purnima.jain.bank.account.json;

import java.time.Instant;

import com.purnima.jain.bank.account.domain.CustomBankAccountHistory;

import lombok.Data;

@Data
public class CustomBankAccountHistoryResponseDto {

	private String accountNumber;
	private String description;
	private Instant timestamp;

	public CustomBankAccountHistoryResponseDto(CustomBankAccountHistory customBankAccountHistory) {
		this.accountNumber = customBankAccountHistory.getAccountNumber();
		this.description = customBankAccountHistory.getDescription();
		this.timestamp = customBankAccountHistory.getTimestamp();
	}

}
