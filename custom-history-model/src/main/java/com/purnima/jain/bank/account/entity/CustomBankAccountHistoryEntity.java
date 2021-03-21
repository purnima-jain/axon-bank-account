package com.purnima.jain.bank.account.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ACCOUNT_CUSTOM_HISTORY")
public class CustomBankAccountHistoryEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String accountNumber;
	private String description;
	private Instant timestamp;

}
