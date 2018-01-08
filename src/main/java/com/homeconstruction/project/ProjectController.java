package com.homeconstruction.project;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller()
public class ProjectController {

    private final CommandGateway commandGateway;
    private ProjectQueryRepository projectQueryRepository;

    @Autowired
    public ProjectController(CommandGateway commandGateway, ProjectQueryRepository projectQueryRepository) {
        this.commandGateway = commandGateway;
        this.projectQueryRepository = projectQueryRepository;
    }

    @RequestMapping("/project")
    @ResponseBody
    public Iterable<Project> findAll() {

        return projectQueryRepository.findAll();
    }

    @RequestMapping("/project/{id}")
    @ResponseBody
    public Project find(@PathVariable("id") String id) {

        return projectQueryRepository.findOne(id);
    }

    @RequestMapping(value = "/project/create/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String create(@PathVariable String name) {

        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateProjectCommand(id, name), LoggingCallback.INSTANCE);

        return id;
    }
}
