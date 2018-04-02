package com.homeconstruction;

import com.homeconstruction.home.api.*;
import com.homeconstruction.home.command.HomeCommandService;
import com.homeconstruction.home.command.HomeTypeCommandService;
import com.homeconstruction.home.query.HomeQueryService;
import com.homeconstruction.home.query.HomeTypeProjection;
import com.homeconstruction.home.query.HomeTypeQueryService;
import com.homeconstruction.project.api.*;
import com.homeconstruction.project.command.ProjectCommandService;
import com.homeconstruction.project.query.ProjectProjection;
import com.homeconstruction.project.query.ProjectQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
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
    private HomeTypeCommandService homeTypeCommandService;

    @Autowired
    private HomeCommandService homeCommandService;

    @Autowired
    private HomeTypeQueryService homeTypeQueryService;

    @Autowired
    private HomeQueryService homeQueryService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        LOG.info("Bootstrapping data...");

        String projectId = initializeProjects();
        if(projectId != null) {

            String homeTypeId = initializeHomeTypes(projectId);

            if(homeTypeId != null) {
                initializeHomes(new HomeTypeId(homeTypeId));
            }
        }

        LOG.info("...Bootstrapping completed");
    }

    private String initializeProjects() {

        Optional<ProjectProjection> project = projectQueryService.findByName(WITTE_BRUGGEN);
        if(project.isPresent())
        {
            return project.get().getId();
        }

        LOG.info("... initialize projects");

        String projectId = UUID.randomUUID().toString();

        InitiateProject initiateProject = new InitiateProject(projectId,
                WITTE_BRUGGEN);
        projectCommandService.initiateProject(initiateProject);

        ReachProjectTarget reachProjectTarget = new ReachProjectTarget(projectId,
                new MinimumAmountOfBuyersReachedPercentage(70), LocalDate.of(2017, 6, 14));
        projectCommandService.reachProjectTarget(reachProjectTarget);

        StartConstructionOnSite startConstructionOnSite = new StartConstructionOnSite(projectId,
                LocalDate.of(2017, 12, 2));
        projectCommandService.startConstructionOnSite(startConstructionOnSite);

        return projectId;
    }

    private String initializeHomeTypes(String projectId) {

        HomeTypeKey homeTypeKey = new HomeTypeKey("E");

        Optional<HomeTypeProjection> homeType = homeTypeQueryService.findByHomeType(WITTE_BRUGGEN, homeTypeKey);
        if(homeType.isPresent())
        {
            return homeType.get().getId();
        }

        LOG.info("... initialize home types");

        String homeTypeId = UUID.randomUUID().toString();

        DefineHomeType defineHomeType = new DefineHomeType(homeTypeId, projectId, homeTypeKey, new HomeTypeDescription("2 onder 1 kap"));
        homeTypeCommandService.defineHomeType(defineHomeType);

        return homeTypeId;
    }

    private void initializeHomes(HomeTypeId homeTypeId) {

        ProjectNumber projectNumber = new ProjectNumber(8);

        if(homeQueryService.findByProjectNumber(WITTE_BRUGGEN, projectNumber).isPresent())
        {
            return;
        }

        LOG.info("... initialize homes");

        String homeId = UUID.randomUUID().toString();

        //Note: fictional price
        DefineHome defineHome = new DefineHome(homeId, homeTypeId, projectNumber,
                new LotSize(252), new AreaOfUse(166), new Price(1000000));
        homeCommandService.defineHome(defineHome);
    }
}