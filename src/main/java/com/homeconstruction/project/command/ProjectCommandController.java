package com.homeconstruction.project.command;

import com.homeconstruction.project.api.InitiateProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ProjectCommandController {

    private final ProjectCommandService projectCommandService;

    @Autowired
    public ProjectCommandController(ProjectCommandService projectCommandService) {

        this.projectCommandService = projectCommandService;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void initiateProject(@RequestBody InitiateProject initiateProject) {

        projectCommandService.initiateProject(initiateProject);
    }
}
