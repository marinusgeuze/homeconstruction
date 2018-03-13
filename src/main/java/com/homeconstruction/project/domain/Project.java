package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.InitiateProjectCommand;
import com.homeconstruction.project.api.ProjectInitiatedEvent;
import com.homeconstruction.project.api.ProjectName;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
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
    private ProjectName name;

    @CommandHandler
    public Project(InitiateProjectCommand command) {

        apply(new ProjectInitiatedEvent(command.getId(), command.getName()));
    }

    @EventHandler
    public void on(ProjectInitiatedEvent event) {

        this.id = event.getId();
        this.name = event.getName();
    }

    public String getId() {
        return id;
    }

    public ProjectName getName() {
        return name;
    }
}

