package com.homeconstruction.home.domain;

import com.homeconstruction.project.api.InitiateProject;
import com.homeconstruction.project.api.ProjectInitiated;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Aggregate(repository = "homeCommandRepository")
@Entity
@NoArgsConstructor
public class Home {

    @Id
    @AggregateIdentifier
    private String id;

    @CommandHandler
    public Home(InitiateProject command) {

        //apply(new ProjectInitiated(command.getId(), command.getName()));
    }

    @EventHandler
    public void on(ProjectInitiated event) {

        this.id = event.getId();
    }

    public String getId() {
        return id;
    }
}

