package com.homeconstruction.buyer.domain;

import com.homeconstruction.buyer.api.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(repository = "buyerCommandRepository")
@Entity
@NoArgsConstructor
public class Buyer {

    //@EmbeddedId
    @Id
    @AggregateIdentifier
    //private BuyerId id;
    private String id;
    private Firstname firstname;
    private Surname surname;
    private Address address;

    @CommandHandler
    public Buyer(AddBuyer command) {

        apply(new BuyerAdded(command.getId(), command.getFirstname(), command.getSurname(),
                command.getAddress()));
    }

    @EventHandler
    public void on(BuyerAdded event) {

        this.id = event.getId();
        this.firstname = event.getFirstname();
        this.surname = event.getSurname();
        this.address = event.getAddress();
    }
}