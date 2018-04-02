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

        MinimumAmountOfBuyersReachedPercentage minimumAmountOfBuyersReachedPercentage = new MinimumAmountOfBuyersReachedPercentage(70);
        fixture.given(new ProjectInitiated(PROJECT_ID, new ProjectName("Witte Bruggen")))
                .when(new ReachProjectTarget(PROJECT_ID, minimumAmountOfBuyersReachedPercentage, LocalDate.now()))
                .expectEvents(new ProjectTargetReached(PROJECT_ID, minimumAmountOfBuyersReachedPercentage, LocalDate.now()));
    }

    @Test
    public void testStartProject() throws Exception {

        LocalDate startDate = LocalDate.now();
        fixture.given(new ProjectInitiated(PROJECT_ID, new ProjectName("Witte Bruggen")),
                new ProjectTargetReached(PROJECT_ID, new MinimumAmountOfBuyersReachedPercentage(70), LocalDate.now()))
                .when(new StartConstructionOnSite(PROJECT_ID, startDate))
                .expectEvents(new ConstructionOnSiteStarted(PROJECT_ID, startDate));
    }
}