package com.homeconstruction.ui.customer;

import com.homeconstruction.project.query.ProjectProjection;
import com.homeconstruction.project.query.ProjectQueryService;
import org.springframework.stereotype.Service;

@Service
public class CustomerUIService {

    private final ProjectQueryService projectQueryService;

    public CustomerUIService(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
    }

    public ProjectProjection findProjectByNameAndHomeNumber(String name, int homeNumber) {

        return projectQueryService.findByName(name).orElseThrow(() ->
                new RuntimeException(String.format("Project with name %s does not exist!", name)));
    }
}