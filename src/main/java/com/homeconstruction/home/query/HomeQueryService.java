package com.homeconstruction.home.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeQueryService {

    private HomeQueryRepository homeQueryRepository;

    @Autowired
    public HomeQueryService(HomeQueryRepository homeQueryRepository) {
        this.homeQueryRepository = homeQueryRepository;
    }

    public Optional<HomeProjection> findByProjectNumber(String projectName, Integer projectNumber) {

        return homeQueryRepository.findByHousenumber(projectNumber);
    }
}