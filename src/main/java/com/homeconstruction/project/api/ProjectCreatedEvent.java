package com.homeconstruction.project.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectCreatedEvent {

    private String id;
    private String name;

    public ProjectCreatedEvent(String id, String name) {
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
