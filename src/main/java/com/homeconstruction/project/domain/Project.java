package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(repository = "projectCommandRepository")
@Entity
@NoArgsConstructor
public class Project {

    @Id
    @AggregateIdentifier
    private ProjectId id;
    private ProjectName name;
    @Basic(optional=true)
    private Boolean minimumAmountOfBuyersTargetReached;
    @Basic(optional=true)
    private MinimumAmountOfBuyersReachedPercentage minimumAmountOfBuyersReachedPercentage;
    @Basic(optional=true)
    private LocalDate minimumAmountOfBuyersTargetReachedDate;
    @Basic(optional=true)
    private LocalDate constructionOnSiteStartDate;

    @CommandHandler
    public Project(InitiateProject command) {

        apply(new ProjectInitiated(command.getId(), command.getName()));
    }

    @EventHandler
    public void on(ProjectInitiated event) {

        this.id = event.getId();
        this.name = event.getName();
    }

    @CommandHandler
    public void handle(ReachProjectTarget command) {

        apply(new ProjectTargetReached(command.getId(), command.getPercentage(), command.getReachedDate()));
    }

    @EventHandler
    public void on(ProjectTargetReached event) {

        this.minimumAmountOfBuyersTargetReached = true;
        this.minimumAmountOfBuyersReachedPercentage = event.getPercentage();
        this.minimumAmountOfBuyersTargetReachedDate = event.getReachedDate();
    }

    @CommandHandler
    public void handle(StartConstructionOnSite command) {

        apply(new ConstructionOnSiteStarted(command.getId(), command.getStartDate()));
    }

    @EventHandler
    public void on(ConstructionOnSiteStarted event) {

        this.constructionOnSiteStartDate = event.getStartDate();
    }
}

