package com.purnima.jain.bank.account.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BaseCommand<T> {

	@TargetAggregateIdentifier
	protected final T id;

	protected BaseCommand(T id) {
		this.id = id;
	}

}
