package com.homeconstruction.project.domain;

import com.homeconstruction.project.api.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

public class ProjectTest {

    private static final String PROJECT_ID = UUID.randomUUID().toString();

    private AggregateTestFixture<Project> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Project.class);
    }

    @Test
    public void testInitiateProject() throws Exception {

        ProjectName projectName = new ProjectName("Witte Bruggen");
        fixture.givenNoPriorActivity()
                .when(new InitiateProject(PROJECT_ID, projectName))
                .expectEvents(new ProjectInitiated(PROJECT_ID, projectName));
    }

    @Test
    public void testReachProjectTarget() throws Exception {

        ProjectReachedPercentage projectReachedPercentage = new ProjectReachedPercentage(70);
        fixture.given(new ProjectInitiated(PROJECT_ID, new ProjectName("Witte Bruggen")))
                .when(new ReachProjectTarget(PROJECT_ID, projectReachedPercentage))
                .expectEvents(new ProjectTargetReached(PROJECT_ID, projectReachedPercentage));
    }

    @Test
    public void testStartProject() throws Exception {

        LocalDate startDate = LocalDate.now();
        fixture.given(new ProjectInitiated(PROJECT_ID, new ProjectName("Witte Bruggen")),
                new ProjectTargetReached(PROJECT_ID, new ProjectReachedPercentage(70)))
                .when(new StartProject(PROJECT_ID, startDate))
                .expectEvents(new ProjectStarted(PROJECT_ID, startDate));
    }
}