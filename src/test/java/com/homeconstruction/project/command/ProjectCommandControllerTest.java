package com.homeconstruction.project.command;

import com.homeconstruction.project.query.ProjectQueryRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectCommandController.class, secure = false)
public class ProjectCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectQueryRepository projectQueryRepository;

    //TODO: Implement test
}