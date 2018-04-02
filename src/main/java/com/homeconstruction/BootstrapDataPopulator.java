package com.homeconstruction;

import com.homeconstruction.home.api.*;
import com.homeconstruction.home.command.HomeCommandService;
import com.homeconstruction.home.query.HomeQueryService;
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

    private final static ProjectName WITTE_BRUGGEN = new ProjectName("Witte Bruggen");

    @Autowired
    private ProjectCommandService projectCommandService;

    @Autowired
    private ProjectQueryService projectQueryService;

    @Autowired
    private HomeCommandService homeCommandService;

    @Autowired
    private HomeQueryService homeQueryService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        LOG.info("Bootstrapping data...");

        initializeProjects();
        initializeHomes();

        LOG.info("...Bootstrapping completed");
    }

    private void initializeProjects() {

        if(projectQueryService.findByName(WITTE_BRUGGEN).isPresent())
        {
            return;
        }

        LOG.info("... initialize projects");

        ProjectId projectId = new ProjectId(UUID.randomUUID().toString());

        InitiateProject initiateProject = new InitiateProject(projectId,
                WITTE_BRUGGEN);
        projectCommandService.initiateProject(initiateProject);

        ReachProjectTarget reachProjectTarget = new ReachProjectTarget(projectId,
                new MinimumAmountOfBuyersReachedPercentage(70), LocalDate.of(2017, 6, 14));
        projectCommandService.reachProjectTarget(reachProjectTarget);

        StartConstructionOnSite startConstructionOnSite = new StartConstructionOnSite(projectId,
                LocalDate.of(2017, 12, 2));
        projectCommandService.startConstructionOnSite(startConstructionOnSite);
    }

    /*
        String homeTypeId = UUID.randomUUID().toString();
        DefineHomeType defineHomeType = new DefineHomeType(homeTypeId, "E", "2 onder 1 kap");
        homeCommandService.defineHomeType(defineHomeType);
  */

    private void initializeHomes() {

        ProjectNumber projectNumber = new ProjectNumber(8);

        if(homeQueryService.findByProjectNumber(WITTE_BRUGGEN, projectNumber).isPresent())
        {
            return;
        }

        LOG.info("... initialize homes");

        HomeId homeId = new HomeId(UUID.randomUUID().toString());
        //Note: fictional price
        DefineHome defineHome = new DefineHome(homeId, projectNumber,
                new LotSize(252), new AreaOfUse(166), new Price(1000000));
        homeCommandService.defineHome(defineHome);
    }
}