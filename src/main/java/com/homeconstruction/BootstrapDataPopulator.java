package com.homeconstruction;

import com.homeconstruction.buyer.api.*;
import com.homeconstruction.buyer.command.BuyerCommandService;
import com.homeconstruction.buyer.query.BuyerProjection;
import com.homeconstruction.buyer.query.BuyerQueryService;
import com.homeconstruction.home.api.*;
import com.homeconstruction.home.command.HomeCommandService;
import com.homeconstruction.home.command.HomeTypeCommandService;
import com.homeconstruction.home.query.HomeProjection;
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
    private HomeTypeQueryService homeTypeQueryService;

    @Autowired
    private HomeCommandService homeCommandService;

    @Autowired
    private HomeQueryService homeQueryService;

    @Autowired
    private BuyerCommandService buyerCommandService;

    @Autowired
    private BuyerQueryService buyerQueryService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        LOG.info("Bootstrapping data...");

        String projectId = initializeProjects();
        String homeTypeId = initializeHomeTypes(projectId);
        String homeId = initializeHomes(new HomeTypeId(homeTypeId));
        String buyerId = initializeBuyers(new HomeId(homeId));
        initializeHomeReservations(new HomeId(homeId), new BuyerId(buyerId));
        initializeHomeSells(new HomeId(homeId), new BuyerId(buyerId));
        initializeHomeConstructionStarts(new HomeId(homeId), new BuyerId(buyerId));

        LOG.info("...Bootstrapping completed");
    }

    private String initializeProjects() {

        Optional<ProjectProjection> project = projectQueryService.findByName(WITTE_BRUGGEN);
        if (project.isPresent()) {
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
        if (homeType.isPresent()) {
            return homeType.get().getId();
        }

        LOG.info("... initialize home types");

        String homeTypeId = UUID.randomUUID().toString();

        DefineHomeType defineHomeType = new DefineHomeType(homeTypeId, projectId, homeTypeKey, new HomeTypeDescription("2 onder 1 kap"));
        homeTypeCommandService.defineHomeType(defineHomeType);

        return homeTypeId;
    }

    private String initializeHomes(HomeTypeId homeTypeId) {

        ProjectNumber projectNumber = new ProjectNumber(8);

        Optional<HomeProjection> home = homeQueryService.findByProjectNumber(WITTE_BRUGGEN, projectNumber);
        if (home.isPresent()) {
            return home.get().getId();
        }

        LOG.info("... initialize homes");

        String homeId = UUID.randomUUID().toString();

        //Note: fictional price
        DefineHome defineHome = new DefineHome(homeId, homeTypeId, projectNumber,
                new LotSize(252), new AreaOfUse(166), new Price(1000000));
        homeCommandService.defineHome(defineHome);

        return homeId;
    }

    private String initializeBuyers(HomeId homeId) {

        Surname surname = new Surname("Geuze");

        Optional<BuyerProjection> buyer = buyerQueryService.findBySurname(WITTE_BRUGGEN, surname);
        if (buyer.isPresent()) {
            return buyer.get().getId();
        }

        LOG.info("... initialize buyers");

        String buyerId = UUID.randomUUID().toString();

        //Note: fictional address
        AddBuyer addBuyer = new AddBuyer(buyerId, new Firstname("Marinus"), surname,
                new Address("De mooiste straat 12, 3829XX, Hooglanderveen"));
        buyerCommandService.addBuyer(addBuyer);

        return buyerId;
    }

    private void initializeHomeReservations(HomeId homeId, BuyerId buyerId) {

        Optional<HomeProjection> home = homeQueryService.findByBuyerId(WITTE_BRUGGEN, buyerId);

        if (home.isPresent() && home.get().getReservationDate() != null) {
            return;
        }

        LOG.info("... initialize home reservations");

        ReserveHome reserveHome = new ReserveHome(homeId.getId(), buyerId, new ReservationDate(LocalDate.now()));
        homeCommandService.reserveHome(reserveHome);
    }

    private void initializeHomeSells(HomeId homeId, BuyerId buyerId) {

        Optional<HomeProjection> home = homeQueryService.findByBuyerId(WITTE_BRUGGEN, buyerId);

        if (home.isPresent() && home.get().getSoldDate() != null) {
            return;
        }

        LOG.info("... initialize home sells");

        SellHome sellHome = new SellHome(homeId.getId(), buyerId, new SoldDate(LocalDate.now().plusDays(1)));
        homeCommandService.sellHome(sellHome);
    }

    private void initializeHomeConstructionStarts(HomeId homeId, BuyerId buyerId) {

        Optional<HomeProjection> home = homeQueryService.findByBuyerId(WITTE_BRUGGEN, buyerId);

        if (home.isPresent() && home.get().getConstructionStartDate() != null) {
            return;
        }

        LOG.info("... initialize home construction starts");

        StartHomeConstruction startHomeConstruction = new StartHomeConstruction(homeId.getId(), buyerId, new HomeConstructionStartDate(LocalDate.now().plusDays(2)));
        homeCommandService.startHomeConstruction(startHomeConstruction);
    }
}