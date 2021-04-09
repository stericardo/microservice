package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.config.LoadCustomData;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.BuildingResource;
import com.microservice.api.todo.list.resources.TaskResource;
import com.microservice.api.todo.list.service.BuildingService;
import com.microservice.api.todo.list.service.ProjectService;
import com.microservice.api.todo.list.service.TaskService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BuildingControllerTest extends BaseIntegrationTestAdapter {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private TaskService taskService;

    private Project project = new Project();
    private Building building =  new Building();
    private Task task = new Task();


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
    }

    @Test
    @DisplayName("Create Building - Controller")
    @LoadCustomData
    @Order(1)
    public void createBuildingTest() throws Exception {
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/building/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int size = buildingService.getAll(buildingService.getCrudRepository()).size();
        Assert.assertEquals("Expected 2 ", 2, size);
        Optional<Building> expectedBuilding = buildingService.findById(building.getId());
        boolean resultEvaluation = false;
        if(expectedBuilding.isPresent()){
            resultEvaluation = expectedBuilding.get().equals(building);
            Assert.assertEquals(expectedBuilding.get(), building);
            Assert.assertEquals(true, resultEvaluation);
        }

        Building buildingNull = new Building();
        resultEvaluation = buildingNull.equals(building);
        Assert.assertEquals(false, resultEvaluation);
    }

    @Test
    @DisplayName("Create Building - Controller")
    @LoadCustomData
    @Order(2)
    public void createBuildingNullTest() throws Exception {
        int sizeBefore = buildingService.getAll(buildingService.getCrudRepository()).size();
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(4);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/building/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int size = buildingService.getAll(buildingService.getCrudRepository()).size();
        Assert.assertEquals("Expected 1 ", sizeBefore, size);
    }

    @Test
    @DisplayName("list Buildings ByProject - Controller")
    @LoadCustomData
    @Order(3)
    public void listBuildingsByProjectTest() throws Exception {
        int sizeBefore = buildingService.getAll(buildingService.getCrudRepository()).size();
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/building/project/" + project.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<BuildingResource> buildingResourceList = objectMapper.readValue(contentAsString,
                new TypeReference<List<BuildingResource>>(){});

        Assert.assertEquals("Expected 1 ", sizeBefore, buildingResourceList.size());

    }

    @Test
    @DisplayName("get Building by id - Controller")
    @LoadCustomData
    @Order(4)
    public void getBuildingByIdTest() throws Exception {
        int sizeBefore = buildingService.getAll(buildingService.getCrudRepository()).size();
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/building/" + building.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        BuildingResource buildingResourceExpected = objectMapper.readValue(contentAsString,BuildingResource.class);

        Assert.assertEquals("Expected Building Specific ","Building Specific",
                buildingResourceExpected.getName());

    }

    @Test
    @DisplayName("Get Tasks By Building - Controller")
    @LoadCustomData
    @Order(5)
    public void listTasksByBuildingTest() throws Exception {
        int sizeBefore = buildingService.getAll(buildingService.getCrudRepository()).size();
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/building//tasks/" + building.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Set<TaskResource> taskResourceSet= objectMapper.readValue(contentAsString,
                new TypeReference<Set<TaskResource>>(){});

        Assert.assertEquals("Expected 1",1, taskResourceSet.size());

    }

    @Test
    @DisplayName("List Buildings By Project Null - Controller")
    @LoadCustomData
    @Order(6)
    public void listBuildingsByProjectNullTest() throws Exception {
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/building/project/" + 1000)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<BuildingResource> buildingResourceList = objectMapper.readValue(contentAsString,
                new TypeReference<List<BuildingResource>>(){});

        Assert.assertEquals("Expected 1 ", 0, buildingResourceList.size());

    }

    @Test
    @DisplayName("Get Tasks By Building Null- Controller")
    @LoadCustomData
    @Order(7)
    public void listTasksByBuildingNullTest() throws Exception {
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building 1");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/building//tasks/" + 1000)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Set<TaskResource> taskResourceSet= objectMapper.readValue(contentAsString,
                new TypeReference<Set<TaskResource>>(){});

        Assert.assertEquals("Expected 1",0, taskResourceSet.size());
    }

    @Test
    @DisplayName("Update Building - Controller")
    @LoadCustomData
    @Order(8)
    public void updateBuildingTest() throws Exception {
        int size = buildingService.getAll(buildingService.getCrudRepository()).size();
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setName("Building Changed");
        buildingResource.setDescription("Building1 actions");
        buildingResource.setIdProject(project.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(buildingResource);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/building/"+ building.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int sizeResult = buildingService.getAll(buildingService.getCrudRepository()).size();
        Assert.assertEquals("Expected 9 ", size, sizeResult);
        Optional<Building> expectedBuilding = buildingService.findById(building.getId());
        if(expectedBuilding.isPresent()) {
            Assert.assertEquals(expectedBuilding.get().getName(), "Building Changed");
        }
    }

    @Test
    @DisplayName("Person Delete - Controller")
    @LoadCustomData
    @Order(9)
    public void deletePersonTest() throws Exception {
        int size = buildingService.getAll(buildingService.getCrudRepository()).size();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/building/"+ building.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        int sizeResult = buildingService.getAll(buildingService.getCrudRepository()).size();
        Assert.assertEquals("Expected 0 ", size-1, sizeResult);
    }


}