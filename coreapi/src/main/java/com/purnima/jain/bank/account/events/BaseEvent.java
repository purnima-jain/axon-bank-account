package com.purnima.jain.bank.account.events;

import lombok.Data;

@Data
public class BaseEvent<T> {

	protected final T id;

	protected BaseEvent(T id) {
		this.id = id;
	}

}
