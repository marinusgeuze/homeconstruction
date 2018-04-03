package com.homeconstruction.home.query;

import com.homeconstruction.buyer.api.BuyerId;
import com.homeconstruction.home.api.ProjectNumber;
import com.homeconstruction.project.api.ProjectName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeQueryService {

    private HomeQueryRepository homeQueryRepository;

    public HomeQueryService(HomeQueryRepository homeQueryRepository) {
        this.homeQueryRepository = homeQueryRepository;
    }

    public Optional<HomeProjection> findByProjectNumber(ProjectName projectName, ProjectNumber projectNumber) {

        return homeQueryRepository.findByProjectNumber(projectNumber);
    }

    public Optional<HomeProjection> findByBuyerId(ProjectName witteBruggen, BuyerId buyerId) {

        return homeQueryRepository.findByBuyerId(buyerId);
    }
}