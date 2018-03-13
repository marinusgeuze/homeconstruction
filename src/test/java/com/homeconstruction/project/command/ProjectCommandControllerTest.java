package com.homeconstruction.project.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeconstruction.project.api.InitiateProject;
import com.homeconstruction.project.api.ProjectName;
import com.homeconstruction.project.api.ProjectReachedPercentage;
import com.homeconstruction.project.api.ReachProjectTarget;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ProjectCommandControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private CommandGateway commandGateway;

    //TODO: Can this be done with @WebMvcTest on class level
    @Before
    public void setUp() throws Exception {

        initMocks(this);

        ProjectCommandController controller = new ProjectCommandController(commandGateway);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    public void initiateProject() throws Exception {

        InitiateProject initiateProject = new InitiateProject("1", new ProjectName("Test 1"));

        when(commandGateway.send(initiateProject)).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/project")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(initiateProject));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }
}