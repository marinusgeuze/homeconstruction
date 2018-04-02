package com.homeconstruction.home.query;

import com.homeconstruction.home.api.HomeTypeId;
import com.homeconstruction.home.api.HomeTypeKey;
import com.homeconstruction.home.api.ProjectNumber;
import com.homeconstruction.project.api.ProjectName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeTypeQueryService {

    private HomeTypeQueryRepository homeTypeQueryRepository;

    public HomeTypeQueryService(HomeTypeQueryRepository homeTypeQueryRepository) {
        this.homeTypeQueryRepository = homeTypeQueryRepository;
    }

    public Optional<HomeTypeProjection> findById(HomeTypeId id) {

        return homeTypeQueryRepository.findById(id);
    }

    public Optional<HomeTypeProjection> findByHomeType(ProjectName witteBruggen, HomeTypeKey homeTypeKey) {

        return homeTypeQueryRepository.findByHomeType(homeTypeKey);
    }
}