package com.homeconstruction.project.query;

import com.homeconstruction.project.api.ProjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ProjectQueryControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ProjectQueryService projectQueryService;

    //TODO: Can this be done with @WebMvcTest on class level
    @Before
    public void setUp() throws Exception {

        initMocks(this);

        ProjectQueryController controller = new ProjectQueryController(projectQueryService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAll() throws Exception {

        List<ProjectProjection> projects = new ArrayList<>();
        ProjectProjection project1 = new ProjectProjection();
        project1.setId("1");
        project1.setName("Test 1");
        projects.add(project1);
        ProjectProjection project2 = new ProjectProjection();
        project1.setId("2");
        project1.setName("Test 2");
        projects.add(project2);

        when(projectQueryService.findAll()).thenReturn(projects);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/project");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(project1.getId()))
                .andExpect(jsonPath("$[0].name").value(project1.getName()))
                .andExpect(jsonPath("$[1].id").value(project2.getId()))
                .andExpect(jsonPath("$[1].name").value(project2.getName()));
    }

    @Test
    public void findFound() throws Exception {

        ProjectProjection project1 = new ProjectProjection();
        project1.setId("1");
        project1.setName("Test 1");

        when(projectQueryService.findById(new ProjectId("1"))).thenReturn(Optional.of(project1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/project/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(project1.getId()))
                .andExpect(jsonPath("$.name").value(project1.getName()));
    }

    @Test
    public void findNotFound() throws Exception {

        when(projectQueryService.findById(new ProjectId("1"))).thenReturn(Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/project/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }
}