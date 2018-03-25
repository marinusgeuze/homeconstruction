package com.homeconstruction.project.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectQueryService {

    private ProjectQueryRepository projectQueryRepository;

    @Autowired
    public ProjectQueryService(ProjectQueryRepository projectQueryRepository) {
        this.projectQueryRepository = projectQueryRepository;
    }

    public Iterable<ProjectProjection> findAll() {

        return projectQueryRepository.findAll();
    }

    public Optional<ProjectProjection> findById(String id) {

        return projectQueryRepository.findById(id);
    }

    public Optional<ProjectProjection> findByName(String name) {

        return projectQueryRepository.findByName(name);
    }
}
