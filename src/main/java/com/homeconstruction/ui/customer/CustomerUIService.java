package com.homeconstruction.ui.customer;

import com.homeconstruction.buyer.api.BuyerId;
import com.homeconstruction.buyer.query.BuyerProjection;
import com.homeconstruction.buyer.query.BuyerQueryService;
import com.homeconstruction.home.api.HomeTypeId;
import com.homeconstruction.home.api.ProjectNumber;
import com.homeconstruction.home.query.*;
import com.homeconstruction.project.api.ProjectName;
import com.homeconstruction.project.query.ProjectProjection;
import com.homeconstruction.project.query.ProjectQueryService;
import org.springframework.stereotype.Service;

@Service
public class CustomerUIService {

    private final ProjectQueryService projectQueryService;
    private final HomeTypeQueryService homeTypeQueryService;
    private final HomeQueryService homeQueryService;
    private final BuyerQueryService buyerQueryService;

    public CustomerUIService(ProjectQueryService projectQueryService, HomeTypeQueryService homeTypeQueryService, HomeQueryService homeQueryService, BuyerQueryService buyerQueryService) {
        this.projectQueryService = projectQueryService;
        this.homeTypeQueryService = homeTypeQueryService;
        this.homeQueryService = homeQueryService;
        this.buyerQueryService = buyerQueryService;
    }

    ProjectProjection findProjectByName(ProjectName name) {

        return projectQueryService.findByName(name).orElseThrow(() ->
                new RuntimeException(String.format("Project with name %s does not exist!", name)));
    }

    HomeTypeProjection findHomeTypeById(HomeTypeId id) {

        return homeTypeQueryService.findById(id).orElseThrow(() ->
                new RuntimeException(String.format(
                        "Home Type with id %s does not exist!", id.getId())));
    }

    HomeProjection findHomeByProjectAndProjectNumber(ProjectName projectName, ProjectNumber projectNumber) {

        return homeQueryService.findByProjectNumber(projectName, projectNumber).orElseThrow(() ->
                new RuntimeException(String.format(
                        "Home with project number %s by project with name %s does not exist!",
                        projectNumber.getProjectNumber(), projectName.getName())));
    }

    BuyerProjection findBuyerById(BuyerId buyerId) {

        return buyerQueryService.findById(buyerId).orElseThrow(() ->
                new RuntimeException(String.format(
                        "Buyer with id %s does not exist!",
                        buyerId.getId())));
    }
}