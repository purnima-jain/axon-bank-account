package com.purnima.jain.bank.account.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountActivatedEvent extends BaseEvent<String> {

	private final String status;

	public AccountActivatedEvent(String id, String status) {
		super(id);
		this.status = status;
	}

}
