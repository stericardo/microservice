package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.config.LoadCustomData;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.ProjectResource;
import com.microservice.api.todo.list.service.BuildingService;
import com.microservice.api.todo.list.service.PersonService;
import com.microservice.api.todo.list.service.ProjectService;
import com.microservice.api.todo.list.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectControllerTest extends BaseIntegrationTestAdapter {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PersonService personService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private TaskService taskService;

    private Project project = new Project();
    private Building building =  new Building();
    private Task task = new Task();
    private Person person = new Person();


    @Override
    public void loadSpecificData() {
        project.setTitle("Project1");
        project.setDescription("Project Electronics");
        project = projectService.create(project);
        building = new Building();
        building.setName("Building Specific");
        building.setDescription("Specific description");
        building.setProject(project);
        building = buildingService.create(building);
        task.setStatus("Completed");
        task.setName("Task1");
        task.setDescription("Description");
        task.setBuilding(building);
        task = taskService.create(task);
        person.setName("Name");
        person.setEmail("Email");
        person.setPhone("78787787");
        person.setLastName("LastName");
        person.setSecondLastName("SecondLastName");
        person = personService.create(person);
    }

    @Test
    @DisplayName("Create Project - Controller")
    @LoadCustomData
    @Order(1)
    public void createProjectTest() throws Exception {
        ProjectResource projectResource = new ProjectResource();
        projectResource.setTitle("Name");
        projectResource.setDescription("Description");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(projectResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/project/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int size = projectService.getAll(projectService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
    }

    @Test
    @DisplayName("Get Project by Id - Controller")
    @LoadCustomData
    @Order(2)
    public void getPersonByIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "{}";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/project/" + project.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ProjectResource personResourceExpected =
                objectMapper.readValue(contentAsString,ProjectResource.class);
        int size = projectService.getAll(projectService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
        Optional<Project> expectedProject = projectService.findById(project.getId());
        boolean resultEvaluation = false;
        if(expectedProject.isPresent()){
            resultEvaluation = expectedProject.get().equals(project);
            Assert.assertEquals(expectedProject.get(), project);
            Assert.assertEquals(true, resultEvaluation);
        }

        Project projectNull = new Project();
        resultEvaluation = projectNull.equals(project);
        Assert.assertEquals(false, resultEvaluation);
    }

    @Test
    @DisplayName("Update Project - Controller")
    @LoadCustomData
    @Order(3)
    public void updateProjectTest() throws Exception {
        int size = personService.getAll(projectService.getCrudRepository()).size();
        ProjectResource projectResource = new ProjectResource();
        projectResource.setTitle("Changed title");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(projectResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/project/"+ project.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int sizeResult = projectService.getAll(projectService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", size, sizeResult);
        Optional<Project> expectedProject = projectService.findById(project.getId());
        if(expectedProject.isPresent()) {
            Assert.assertEquals(expectedProject.get().getTitle(), "Changed title");
        }
    }

    @Test
    @DisplayName("Project Delete - Controller")
    @LoadCustomData
    @Order(4)
    public void deleteProjectTest() throws Exception {
        int size = projectService.getAll(projectService.getCrudRepository()).size();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/project/"+ project.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int sizeResult = projectService.getAll(projectService.getCrudRepository()).size();
        Assert.assertEquals("Expected 0 ", size-1, sizeResult);
    }

}