package com.homeconstruction.ui.customer;

import com.homeconstruction.home.api.ProjectNumber;
import com.homeconstruction.home.query.HomeProjection;
import com.homeconstruction.home.query.HomeQueryService;
import com.homeconstruction.project.api.ProjectName;
import com.homeconstruction.project.query.ProjectProjection;
import com.homeconstruction.project.query.ProjectQueryService;
import org.springframework.stereotype.Service;

@Service
public class CustomerUIService {

    private final ProjectQueryService projectQueryService;
    private final HomeQueryService homeQueryService;

    public CustomerUIService(ProjectQueryService projectQueryService, HomeQueryService homeQueryService) {
        this.projectQueryService = projectQueryService;
        this.homeQueryService = homeQueryService;
    }

    ProjectProjection findProjectByName(ProjectName name) {

        return projectQueryService.findByName(name).orElseThrow(() ->
                new RuntimeException(String.format("Project with name %s does not exist!", name)));
    }

    HomeProjection findHomeByProjectAndProjectNumber(ProjectName projectName, ProjectNumber projectNumber) {

        return homeQueryService.findByProjectNumber(projectName, projectNumber).orElseThrow(() ->
                new RuntimeException(String.format(
                        "Home with project number %s by project with name %s does not exist!", projectNumber, projectName)));
    }
}