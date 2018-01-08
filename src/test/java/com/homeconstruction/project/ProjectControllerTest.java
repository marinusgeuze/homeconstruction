package com.homeconstruction.project;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class, secure = false)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectQueryRepository projectQueryRepository;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private EventBus eventBus;

    @MockBean
    private Repository<Project> projectCommandRepository;

    @MockBean
    private EntityManagerProvider entityManagerProvider;

    @MockBean
    private SpringTransactionManager axonTransactionManager;

    //TODO: Find a way to construct projects? Make read projections? Resolved all required MockBeans also I guess
    //TODO: Improve asserts

    @Test
    public void findAll() throws Exception {

        List<Project> projects = new ArrayList<Project>();
        Project project1 = new Project("1", "Test 1");
        projects.add(project1);
        Project project2 = new Project("2", "Test 2");
        projects.add(project2);

        Mockito.when(
                projectQueryRepository.findAll()).thenReturn(projects);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/project");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":\"1\", \"name\":\"Test 1\"},{\"id\":\"2\", \"name\":\"Test 2\"}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void find() throws Exception {

        Project project1 = new Project("1", "Test 1");

        Mockito.when(
                projectQueryRepository.findOne("1")).thenReturn(project1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/project/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":\"1\", \"name\":\"Test 1\"}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}