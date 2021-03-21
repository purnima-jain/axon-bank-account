package com.purnima.jain.bank.account.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.purnima.jain.bank.account.commands.CreateAccountCommand;
import com.purnima.jain.bank.account.commands.CreditMoneyCommand;
import com.purnima.jain.bank.account.commands.DebitMoneyCommand;
import com.purnima.jain.bank.account.events.AccountActivatedEvent;
import com.purnima.jain.bank.account.events.AccountCreatedEvent;
import com.purnima.jain.bank.account.events.AccountHeldEvent;
import com.purnima.jain.bank.account.events.MoneyCreditedEvent;
import com.purnima.jain.bank.account.events.MoneyDebitedEvent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@Data
@Slf4j
// This class cannot go in a separate module; it has to stay in the same module as CommandGateway
public class AccountAggregate {

	@AggregateIdentifier
	private String id;

	private double accountBalance;
	private String currency;
	private Status status;

	public AccountAggregate() {

	}

	@CommandHandler
	public AccountAggregate(CreateAccountCommand createAccountCommand) {
		log.info("Inside the AccountAggregate::constructor-command-handler for CreateAccountCommand sending AccountCreatedEvent via AggregateLifecycle........");
		AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.getAccountBalance(), createAccountCommand.getCurrency()));
	}

	@EventSourcingHandler
	protected void on(AccountCreatedEvent accountCreatedEvent) {
		log.info("Inside the AccountAggregate::EventSourcingHandler for AccountCreatedEvent, sending AccountActivatedEvent via AggregateLifecycle........");
		this.id = accountCreatedEvent.getId();
		this.accountBalance = accountCreatedEvent.getAccountBalance();
		this.currency = accountCreatedEvent.getCurrency();
		this.status = Status.CREATED;

		AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED.toString()));
	}

	@EventSourcingHandler
	protected void on(AccountActivatedEvent accountActivatedEvent) {
		log.info(
				"Inside the AccountAggregate::EventSourcingHandler for AccountActivatedEvent, sending no events........");
		this.status = Status.valueOf(accountActivatedEvent.getStatus());
	}

	@CommandHandler
	protected void handle(CreditMoneyCommand creditMoneyCommand) {
		log.info("Inside the AccountAggregate::command-handler for CreditMoneyCommand sending MoneyCreditedEvent via AggregateLifecycle........");
		AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.getCreditAmount(), creditMoneyCommand.getCurrency()));
	}

	@EventSourcingHandler
	protected void on(MoneyCreditedEvent moneyCreditedEvent) {
		log.info("Inside the AccountAggregate::EventSourcingHandler for MoneyCreditedEvent, may or may not send AccountActivatedEvent via AggregateLifecycle........");
		if (this.accountBalance < 0 && (this.accountBalance + moneyCreditedEvent.getCreditAmount() >= 0)) {
			AggregateLifecycle.apply(new AccountActivatedEvent(moneyCreditedEvent.getId(), Status.ACTIVATED.toString()));
		}

		this.accountBalance = this.accountBalance + moneyCreditedEvent.getCreditAmount();
	}

	@CommandHandler
	protected void handle(DebitMoneyCommand debitMoneyCommand) {
		log.info("Inside the AccountAggregate::command-handler for DebitMoneyCommand sending MoneyDebitedEvent via AggregateLifecycle........");
		AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.getId(), debitMoneyCommand.getDebitAmount(), debitMoneyCommand.getCurrency()));
	}

	@EventSourcingHandler
	protected void on(MoneyDebitedEvent moneyDebitedEvent) {
		log.info("Inside the AccountAggregate::EventSourcingHandler for MoneyDebitedEvent, may or may not send AccountHeldEvent via AggregateLifecycle........");
		if (this.accountBalance >= 0 && (this.accountBalance - moneyDebitedEvent.getDebitAmount()) < 0) {
			AggregateLifecycle.apply(new AccountHeldEvent(moneyDebitedEvent.getId(), Status.HOLD.toString()));
		}
		this.accountBalance = this.accountBalance - moneyDebitedEvent.getDebitAmount();
	}

	@EventSourcingHandler
	protected void on(AccountHeldEvent accountHeldEvent) {
		log.info("Inside the AccountAggregate::EventSourcingHandler for AccountHeldEvent, sending no events........");
		this.status = Status.valueOf(accountHeldEvent.getStatus());
	}

}
