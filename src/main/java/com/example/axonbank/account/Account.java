package com.example.axonbank.account;

import com.example.axonbank.coreapi.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

//@Aggregate(repository = "jpaAccountRepository")
@Aggregate
//@Entity
@NoArgsConstructor
public class Account {

    //@Id
    @AggregateIdentifier
    private String accountId;
    //@Basic
    private int balance;
    //@Basic
    private int overdraftLimit;

    @CommandHandler
    public Account(CreateAccountCommand command) {

        apply(new AccountCreatedEvent(command.getAccountId(), command.getOverdraftLimit()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command) throws OverdraftLimitExceededException {

        if(balance + overdraftLimit < command.getAmount()) {
            throw new OverdraftLimitExceededException();
        }

        apply(new MoneyWithdrawnEvent(accountId, command.getTransactionId(), command.getAmount(), balance - command.getAmount()));
    }

    @CommandHandler
    public void handle(DepositMoneyCommand cmd) {
        apply(new MoneyDepositedEvent(accountId, cmd.getTransactionId(), cmd.getAmount(), balance + cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {

        this.accountId = event.getAccountId();
        this.overdraftLimit = event.getOverdraftLimit();
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event) {
        this.balance = event.getBalance();
    }
}
