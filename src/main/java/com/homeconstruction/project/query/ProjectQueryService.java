package com.homeconstruction.project.query;

import com.homeconstruction.project.api.ProjectId;
import com.homeconstruction.project.api.ProjectName;
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

    Optional<ProjectProjection> findById(ProjectId id) {

        return projectQueryRepository.findById(id);
    }

    public Optional<ProjectProjection> findByName(ProjectName name) {

        return projectQueryRepository.findByName(name);
    }
}
