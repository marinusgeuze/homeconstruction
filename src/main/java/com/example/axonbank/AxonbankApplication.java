package com.example.axonbank;

import com.example.axonbank.coreapi.CreateAccountCommand;
import com.example.axonbank.coreapi.RequestMoneyTransferCommand;
import com.example.axonbank.coreapi.WithdrawMoneyCommand;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@EnableAutoConfiguration
@SpringBootApplication
public class AxonbankApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext config = SpringApplication.run(AxonbankApplication.class, args);
		CommandBus commandBus = config.getBean(CommandBus.class);

		commandBus.dispatch(asCommandMessage(new CreateAccountCommand("1234", 500)));
		commandBus.dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));

		//config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx1", 250)));
		//config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx2", 251)));
		commandBus.dispatch(asCommandMessage(new RequestMoneyTransferCommand("tf1", "1234", "4321", 100)));

	}
/*
	@Bean
	public Repository<Account> jpaAccountRepository(EventBus eventBus) {
		return new GenericJpaRepository<Account>(entityManagerProvider(), Account.class, eventBus);
	}

	@Bean
	public EntityManagerProvider entityManagerProvider() {
		return new ContainerManagedEntityManagerProvider();
	}

	@Bean
	public SpringTransactionManager axonTranscationManager(PlatformTransactionManager tx) {
		return new SpringTransactionManager(tx);
	}

	@Bean
	public CommandBus commandBus() {
		return new AsynchronousCommandBus();
	}

	@Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}
*/
	@Bean
	public EventStorageEngine eventStorageEngine() {
		return new InMemoryEventStorageEngine();
	}
}
