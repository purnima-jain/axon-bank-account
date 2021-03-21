package com.purnima.jain.bank.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AccountEntity {
	
	@Id
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

}
