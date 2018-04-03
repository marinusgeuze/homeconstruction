package com.homeconstruction.home.domain;

import com.homeconstruction.home.api.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Basic;
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
    //private BuyerId buyerId;
    @Basic(optional = true)
    private String buyerId;
    @Basic(optional = true)
    private ReservationDate reservationDate;
    @Basic(optional = true)
    private SoldDate soldDate;
    @Basic(optional = true)
    private HomeConstructionStartDate constructionStartDate;

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

    @CommandHandler
    public void handle(ReserveHome command) {

        //TODO: Add validations (home not already reserved, buyer exists (in domain service))
        apply(new HomeReserved(command.getId(), command.getBuyerId(), command.getReservationDate()));
    }

    @EventHandler
    public void on(HomeReserved event) {

        this.id = event.getId();
        this.buyerId = event.getBuyerId().getId();
        this.reservationDate = event.getReservationDate();
    }

    @CommandHandler
    public void handle(SellHome command) {

        //TODO: Add validations (home not already sold, buyer exists (in domain service))
        apply(new HomeSold(command.getId(), command.getBuyerId(), command.getSoldDate()));
    }

    @EventHandler
    public void on(HomeSold event) {

        this.id = event.getId();
        this.buyerId = event.getBuyerId().getId();
        this.soldDate = event.getSoldDate();
    }


    @CommandHandler
    public void handle(StartHomeConstruction command) {

        //TODO: Add validations (home construction not already started, buyer exists (in domain service))
        apply(new HomeConstructionStarted(command.getId(), command.getBuyerId(), command.getConstructionStartDate()));
    }

    @EventHandler
    public void on(HomeConstructionStarted event) {

        this.id = event.getId();
        this.buyerId = event.getBuyerId().getId();
        this.constructionStartDate = event.getConstructionStartDate();
    }
}