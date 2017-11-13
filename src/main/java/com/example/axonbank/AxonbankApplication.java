package com.example.axonbank;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AxonbankApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext config = SpringApplication.run(AxonbankApplication.class, args);
		CommandBus commandBus = config.getBean(CommandBus.class);

		//commandBus.dispatch(asCommandMessage(new CreateAccountCommand("1234", 500)));
		//commandBus.dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));

		//config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx1", 250)));
		//config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand("4321", "tx2", 251)));
		//commandBus.dispatch(asCommandMessage(new RequestMoneyTransferCommand("tf1", "1234", "4321", 100)));

	}

	@Autowired
	public void configure(EventHandlingConfiguration config) {

		config.registerTrackingProcessor("TransactionHistory");
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
}
