package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.config.LoadCustomData;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.PersonResource;
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
class PersonControllerTest extends BaseIntegrationTestAdapter {

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
    @DisplayName("Create Person - Controller")
    @LoadCustomData
    @Order(1)
    public void createPersonTest() throws Exception {
        PersonResource personResource = new PersonResource();
        personResource.setName("Name");
        personResource.setEmail("Email");
        personResource.setPhone("78787787");
        personResource.setLastName("LastName");
        personResource.setSecondLastName("SecondLastName");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(personResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/person/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int size = personService.getAll(personService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
    }

    @Test
    @DisplayName("Get Person by Id - Controller")
    @LoadCustomData
    @Order(2)
    public void getPersonByIdTest() throws Exception {
        PersonResource personResource = new PersonResource();
        personResource.setName("Name");
        personResource.setEmail("Email");
        personResource.setPhone("78787787");
        personResource.setLastName("LastName");
        personResource.setSecondLastName("SecondLastName");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(personResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/person/" + person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        PersonResource personResourceExpected =
                objectMapper.readValue(contentAsString,PersonResource.class);
        int size = personService.getAll(personService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
        Optional<Person> expectedPerson = personService.findById(person.getId());
        boolean resultEvaluation = false;
        if(expectedPerson.isPresent()){
            resultEvaluation = expectedPerson.get().equals(person);
            Assert.assertEquals(expectedPerson.get(), person);
            Assert.assertEquals(true, resultEvaluation);
        }

        Person personNull = new Person();
        resultEvaluation = personNull.equals(person);
        Assert.assertEquals(false, resultEvaluation);

    }

    @Test
    @DisplayName("Update Building - Controller")
    @LoadCustomData
    @Order(3)
    public void updateBuildingTest() throws Exception {
        int size = personService.getAll(personService.getCrudRepository()).size();
        PersonResource personResource = new PersonResource();
        personResource.setName("NameChanged");
        personResource.setEmail("Email");
        personResource.setPhone("78787787");
        personResource.setLastName("LastName");
        personResource.setSecondLastName("SecondLastName");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(personResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/person/"+ person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int sizeResult = personService.getAll(personService.getCrudRepository()).size();
        Assert.assertEquals("Expected 9 ", size, sizeResult);
        Optional<Person> expectedPerson = personService.findById(person.getId());
        if(expectedPerson.isPresent()) {
            Assert.assertEquals(expectedPerson.get().getName(), "NameChanged");
        }
    }

    @Test
    @DisplayName("Person Delete - Controller")
    @LoadCustomData
    @Order(4)
    public void deletePersonTest() throws Exception {
        int size = personService.getAll(personService.getCrudRepository()).size();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/person/"+ person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int sizeResult = personService.getAll(personService.getCrudRepository()).size();
        Assert.assertEquals("Expected 0 ", size-1, sizeResult);
    }

}