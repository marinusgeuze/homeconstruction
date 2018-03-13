package com.homeconstruction.project.command;

import com.homeconstruction.project.api.InitiateProject;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ProjectCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ProjectCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void initiateProjectCommand(@RequestBody InitiateProject initiateProject) {

        //TODO: Change this to async implementation
        commandGateway.send(initiateProject, LoggingCallback.INSTANCE);
    }
}
