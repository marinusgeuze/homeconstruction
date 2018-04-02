package com.homeconstruction.project.query;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectQueryService {

    private ProjectQueryRepository projectQueryRepository;

    public ProjectQueryService(ProjectQueryRepository projectQueryRepository) {
        this.projectQueryRepository = projectQueryRepository;
    }

    Iterable<ProjectProjection> findAll() {

        return projectQueryRepository.findAll();
    }

    Optional<ProjectProjection> findById(String id) {

        return projectQueryRepository.findById(id);
    }

    Optional<ProjectProjection> findByName(String name) {

        return projectQueryRepository.findByName(name);
    }
}
