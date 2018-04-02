package com.homeconstruction.home.domain;

import com.homeconstruction.home.api.DefineHomeType;
import com.homeconstruction.home.api.HomeTypeDefined;
import com.homeconstruction.home.api.HomeTypeDescription;
import com.homeconstruction.home.api.HomeTypeKey;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate(repository = "homeTypeCommandRepository")
@Entity
@NoArgsConstructor
public class HomeType {

    //@EmbeddedId
    @Id
    @AggregateIdentifier
    //private HomeTypeId id;
    private String id;
    //Do not use Value Object because we do not want to create link to classes in other bounded context
    private String projectId;
    private HomeTypeKey typeKey;
    private HomeTypeDescription description;

    @CommandHandler
    public HomeType(DefineHomeType command) {

        apply(new HomeTypeDefined(command.getId(), command.getProjectId(), command.getTypeKey(),
                command.getDescription()));
    }

    @EventHandler
    public void on(HomeTypeDefined event) {

        this.id = event.getId();
        this.projectId = event.getProjectId();
        this.typeKey = event.getTypeKey();
        this.description = event.getDescription();
    }
}

