package com.homeconstruction.project;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.TestOnly;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(repository = "projectCommandRepository")
@Entity
@NoArgsConstructor
public class Project {

    @Id
    @AggregateIdentifier
    private String id;
    private String name;

    @CommandHandler
    public Project(CreateProjectCommand command) {

        apply(new ProjectCreatedEvent(command.getId(), command.getName()));
    }

    @TestOnly
    public Project(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @EventSourcingHandler
    public void on(ProjectCreatedEvent event) {

        this.id = event.getId();
        this.name = event.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

