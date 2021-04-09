package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.config.LoadCustomData;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.TaskResource;
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
class TaskControllerTest extends BaseIntegrationTestAdapter {

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
    @DisplayName("Create Task - Controller")
    @LoadCustomData
    @Order(1)
    public void createTaskTest() throws Exception {
        TaskResource taskResource = new TaskResource();
        taskResource.setName("Name");
        taskResource.setDescription("Description");
        taskResource.setBuildingId(building.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(taskResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/task/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int size = taskService.getAll(taskService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
    }

    @Test
    @DisplayName("Get Task by Id - Controller")
    @LoadCustomData
    @Order(2)
    public void getPersonByIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "{}";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/task/" + task.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        TaskResource personResourceExpected =
                objectMapper.readValue(contentAsString,TaskResource.class);
        int size = taskService.getAll(taskService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
        Optional<Task> expectedTask = taskService.findById(task.getId());
        boolean resultEvaluation = false;
        if(expectedTask.isPresent()){
            resultEvaluation = expectedTask.get().equals(task);
            Assert.assertEquals(expectedTask.get(), task);
            Assert.assertEquals(true, resultEvaluation);
        }

        Task taskNull = new Task();
        resultEvaluation = taskNull.equals(task);
        Assert.assertEquals(false, resultEvaluation);
    }

    @Test
    @DisplayName("Update Task - Controller")
    @LoadCustomData
    @Order(3)
    public void updateTaskTest() throws Exception {
        int size = taskService.getAll(taskService.getCrudRepository()).size();
        TaskResource taskResource = new TaskResource();
        taskResource.setName("Changed title");
        taskResource.setBuildingId(building.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(taskResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/task/"+ task.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int sizeResult = taskService.getAll(taskService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", size, sizeResult);
        Optional<Task> expectedTask = taskService.findById(task.getId());
        if(expectedTask.isPresent()) {
            Assert.assertEquals(expectedTask.get().getName(), "Changed title");
        }
    }

    @Test
    @DisplayName("delete Task - Controller")
    @LoadCustomData
    @Order(4)
    public void deleteTaskTest() throws Exception {
        int size = taskService.getAll(taskService.getCrudRepository()).size();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/task/"+ task.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int sizeResult = taskService.getAll(taskService.getCrudRepository()).size();
        Assert.assertEquals("Expected 0 ", size-1, sizeResult);
    }

}