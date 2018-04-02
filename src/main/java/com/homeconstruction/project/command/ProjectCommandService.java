package com.homeconstruction.project.command;

import com.homeconstruction.project.api.InitiateProject;
import com.homeconstruction.project.api.ReachProjectTarget;
import com.homeconstruction.project.api.StartConstructionOnSite;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class ProjectCommandService {

    //TODO: Change this to async implementation
    private final CommandGateway commandGateway;

    public ProjectCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void initiateProject(InitiateProject initiateProject) {

        commandGateway.send(initiateProject, LoggingCallback.INSTANCE);
    }

    public void reachProjectTarget(ReachProjectTarget reachProjectTarget) {

        commandGateway.send(reachProjectTarget, LoggingCallback.INSTANCE);
    }

    public void startConstructionOnSite(StartConstructionOnSite startConstructionOnSite) {

        commandGateway.send(startConstructionOnSite, LoggingCallback.INSTANCE);
    }
}