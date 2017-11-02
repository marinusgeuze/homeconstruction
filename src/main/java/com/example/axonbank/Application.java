package com.example.axonbank;

import com.example.axonbank.account.Account;
import com.example.axonbank.coreapi.CreateAccountCommand;
import com.example.axonbank.coreapi.WithdrawMoneyCommand;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

public class Application {

    public static void main(String[] args) {

        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Account.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        config.start();
        config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));
        config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", 250)));
        //config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", 251)));
    }
}
