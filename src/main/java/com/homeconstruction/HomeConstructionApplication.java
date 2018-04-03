package com.homeconstruction;

import com.homeconstruction.buyer.domain.Buyer;
import com.homeconstruction.home.domain.Home;
import com.homeconstruction.home.domain.HomeType;
import com.homeconstruction.project.domain.Project;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class HomeConstructionApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext config = SpringApplication.run(HomeConstructionApplication.class, args);
        CommandBus commandBus = config.getBean(CommandBus.class);
    }

    @Bean
    public Repository<Project> projectCommandRepository(EventBus eventBus) {
        return new GenericJpaRepository<Project>(entityManagerProvider(), Project.class, eventBus);
    }

    @Bean
    public Repository<HomeType> homeTypeCommandRepository(EventBus eventBus) {
        return new GenericJpaRepository<HomeType>(entityManagerProvider(), HomeType.class, eventBus);
    }

    @Bean
    public Repository<Home> homeCommandRepository(EventBus eventBus) {
        return new GenericJpaRepository<Home>(entityManagerProvider(), Home.class, eventBus);
    }

    @Bean
    public Repository<Buyer> buyerCommandRepository(EventBus eventBus) {
        return new GenericJpaRepository<Buyer>(entityManagerProvider(), Buyer.class, eventBus);
    }

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

    @Bean
    public SpringTransactionManager axonTransactionManager(PlatformTransactionManager tx) {
        return new SpringTransactionManager(tx);
    }
}
