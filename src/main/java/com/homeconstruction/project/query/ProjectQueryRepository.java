package com.homeconstruction.project.query;

import com.homeconstruction.project.domain.Project;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface ProjectQueryRepository extends Repository<Project, String> {

    Collection<ProjectProjection> findAll();

    Optional<ProjectProjection> findById(String id);
}
