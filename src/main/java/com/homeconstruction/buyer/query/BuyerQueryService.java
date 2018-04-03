package com.homeconstruction.buyer.query;

import com.homeconstruction.buyer.api.BuyerId;
import com.homeconstruction.buyer.api.Surname;
import com.homeconstruction.project.api.ProjectName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerQueryService {

    private BuyerQueryRepository buyerQueryRepository;

    public BuyerQueryService(BuyerQueryRepository buyerQueryRepository) {
        this.buyerQueryRepository = buyerQueryRepository;
    }

    public Optional<BuyerProjection> findById(BuyerId id) {

        return buyerQueryRepository.findById(id);
    }

    public Optional<BuyerProjection> findBySurname(ProjectName witteBruggen, Surname surname) {

        return buyerQueryRepository.findBySurname(surname);
    }
}