package com.example.axonbank;

import com.example.axonbank.account.Account;
import com.example.axonbank.coreapi.CreateAccountCommand;
import com.example.axonbank.coreapi.RequestMoneyTransferCommand;
import com.example.axonbank.transfer.MoneyTransfer;
import com.example.axonbank.transfer.MoneyTransferSaga;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

public class Application {

    public static void main(String[] args) {

        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Account.class)
                .configureAggregate(MoneyTransfer.class)
                .registerModule(SagaConfiguration.subscribingSagaManager(MoneyTransferSaga.class))
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        config.start();

        config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("1234", 500)));
        config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));

        //config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx1", 250)));
        //config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx2", 251)));
        config.commandBus().dispatch(asCommandMessage(new RequestMoneyTransferCommand("tf1", "1234", "4321", 100)));

        config.shutdown();
    }
}
