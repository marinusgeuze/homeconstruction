package com.homeconstruction.project.api;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@NoArgsConstructor
public class CreateProjectCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;

    public CreateProjectCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
