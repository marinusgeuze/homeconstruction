package com.homeconstruction.project.query;

import com.homeconstruction.framework.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectQueryController {

    private ProjectQueryService projectQueryService;

    @Autowired
    public ProjectQueryController(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
    }

    @RequestMapping("/project")
    @ResponseBody
    public Iterable<ProjectProjection> findAll() {

        return projectQueryService.findAll();
    }

    @RequestMapping("/project/{id}")
    @ResponseBody
    public ProjectProjection find(@PathVariable("id") String id) {

        return projectQueryService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
