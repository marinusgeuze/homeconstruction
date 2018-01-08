package com.homeconstruction.project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectQueryRepository extends CrudRepository<Project, String> {

    //TODO: make own repository which only contains read methods!
}
