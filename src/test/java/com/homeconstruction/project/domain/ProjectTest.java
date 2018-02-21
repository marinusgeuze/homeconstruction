package com.homeconstruction.project.domain;

import com.example.axonbank.coreapi.AccountCreatedEvent;
import com.example.axonbank.coreapi.OverdraftLimitExceededException;
import com.example.axonbank.coreapi.WithdrawMoneyCommand;
import com.homeconstruction.project.api.CreateProjectCommand;
import com.homeconstruction.project.api.ProjectCreatedEvent;
import com.homeconstruction.project.api.ProjectNameAlreadyExistException;
import com.homeconstruction.project.domain.Project;
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
                .when(new CreateProjectCommand(PROJECT_ID, "Witte Bruggen"))
                .expectEvents(new ProjectCreatedEvent(PROJECT_ID, "Witte Bruggen"));
    }

    @Test
    public void testProjectNameAlreadyExist() throws Exception {
        fixture.given(new CreateProjectCommand(UUID.randomUUID().toString(), "Test"))
                .when(new CreateProjectCommand(UUID.randomUUID().toString(), "test "))
                .expectNoEvents()
                .expectException(ProjectNameAlreadyExistException.class);
    }

}