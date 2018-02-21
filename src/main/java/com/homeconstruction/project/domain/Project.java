package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.CreateProjectCommand;
import com.homeconstruction.project.api.ProjectCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

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

        //TODO: Validate if name already exists (create test first)

        apply(new ProjectCreatedEvent(command.getId(), command.getName()));
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

