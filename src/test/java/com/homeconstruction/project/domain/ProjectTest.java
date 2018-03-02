package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.CreateProjectCommand;
import com.homeconstruction.project.api.ProjectCreatedEvent;
import com.homeconstruction.project.api.ProjectName;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class ProjectTest {

    private static final String PROJECT_ID = UUID.randomUUID().toString();

    private AggregateTestFixture<Project> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Project.class);
    }

    @Test
    public void testCreateProject() throws Exception {

        fixture.givenNoPriorActivity()
                .when(new CreateProjectCommand(PROJECT_ID, new ProjectName("Witte Bruggen")))
                .expectEvents(new ProjectCreatedEvent(PROJECT_ID, new ProjectName("Witte Bruggen")));
    }
}