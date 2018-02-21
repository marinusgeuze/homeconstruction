package com.homeconstruction.project.command;

import com.homeconstruction.project.api.CreateProjectCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class ProjectCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ProjectCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/project/create/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String create(@PathVariable String name) {

        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateProjectCommand(id, name), LoggingCallback.INSTANCE);

        return id;
    }
}
