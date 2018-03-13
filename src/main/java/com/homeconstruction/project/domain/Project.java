package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

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
    private String id;
    private ProjectName name;
    private Boolean targetReached;
    private ProjectReachedPercentage targetReachedPercentage;
    private LocalDate startDate;

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

        apply(new ProjectTargetReached(command.getId(), command.getPercentage()));
    }

    @EventHandler
    public void on(ProjectTargetReached event) {

        this.targetReached = true;
        this.targetReachedPercentage = event.getPercentage();
    }

    @CommandHandler
    public void handle(StartProject command) {

        apply(new ProjectStarted(command.getId(), command.getStartDate()));
    }

    @EventHandler
    public void on(ProjectStarted event) {

        this.startDate = event.getStartDate();
    }

    public String getId() {
        return id;
    }

    public ProjectName getName() {
        return name;
    }
}

