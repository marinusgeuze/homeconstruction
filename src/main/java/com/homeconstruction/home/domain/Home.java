package com.homeconstruction.home.domain;

import com.homeconstruction.home.api.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(repository = "homeCommandRepository")
@Entity
@NoArgsConstructor
public class Home {

    //@EmbeddedId
    @Id
    @AggregateIdentifier
    //private HomeId id;
    private String id;
    //private HomeTypeId homeTypeId;
    private String homeTypeId;
    private ProjectNumber projectNumber;
    private LotSize lotSize;
    private AreaOfUse areaOfUse;
    private Price price;

    @CommandHandler
    public Home(DefineHome command) {

        apply(new HomeDefined(command.getId(), command.getHomeTypeId(), command.getProjectNumber(), command.getLotSize(),
                command.getAreaOfUse(), command.getPrice()));
    }

    @EventHandler
    public void on(HomeDefined event) {

        this.id = event.getId();
        this.homeTypeId = event.getHomeTypeId().getId();
        this.projectNumber = event.getProjectNumber();
        this.lotSize = event.getLotSize();
        this.areaOfUse = event.getAreaOfUse();
        this.price = event.getPrice();
    }
/*
    @CommandHandler
    public void handle(ReserveHome command) {

        apply(new HomeReserver(command.getId()));
    }
*/
}

