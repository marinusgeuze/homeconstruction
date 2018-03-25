package com.homeconstruction;

import com.homeconstruction.project.api.*;
import com.homeconstruction.project.command.ProjectCommandService;
import com.homeconstruction.project.query.ProjectQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class BootstrapDataPopulator implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger LOG = LoggerFactory.getLogger(BootstrapDataPopulator.class);

    @Autowired
    ProjectCommandService projectCommandService;

    @Autowired
    ProjectQueryService projectQueryService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        LOG.info("Bootstrapping data...");

        initializeProjects();

        LOG.info("...Bootstrapping completed");
    }

    private void initializeProjects() {

        String witteBruggen = "Witte Bruggen";

        if(projectQueryService.findByName(witteBruggen).isPresent())
        {
            return;
        }

        LOG.info("... initialize projects");

        String projectId = UUID.randomUUID().toString();

        InitiateProject initiateProject = new InitiateProject(projectId,
                new ProjectName(witteBruggen));
        projectCommandService.initiateProject(initiateProject);

        ReachProjectTarget reachProjectTarget = new ReachProjectTarget(projectId,
                new MinimumAmountOfBuyersReachedPercentage(70), LocalDate.of(2017, 6, 14));
        projectCommandService.reachProjectTarget(reachProjectTarget);

        StartConstructionOnSite startConstructionOnSite = new StartConstructionOnSite(projectId,
                LocalDate.of(2017, 12, 2));
        projectCommandService.startConstructionOnSite(startConstructionOnSite);
    }
}