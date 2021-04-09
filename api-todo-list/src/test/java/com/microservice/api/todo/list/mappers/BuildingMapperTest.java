package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.config.LoadCustomData;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.resources.BuildingResource;
import com.microservice.api.todo.list.service.BuildingService;
import com.microservice.api.todo.list.service.ProjectService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BuildingMapperTest extends BaseIntegrationTestAdapter {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BuildingMapper buildingMapper;

    private Project project = new Project();
    private Building building =  new Building();


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
        building = new Building();
        building.setName("Building Specific 2");
        building.setDescription("Specific description 2");
        building.setProject(project);
        building = buildingService.create(building);
    }

    @Test
    @DisplayName("ConvertToEntity Building - Mapper")
    @LoadCustomData
    @Order(1)
    public void convertToEntityTest(){
        BuildingResource buildingResource = new BuildingResource();
        buildingResource.setIdProject(project.getId());
        buildingResource.setDescription("DescResource");
        buildingResource.setName("BuildingName");
        buildingResource.setProjectName(project.getTitle());
        Building building = buildingMapper.convertToEntity(buildingResource);
        Assert.assertEquals(buildingResource.getName(), building.getName());
        Assert.assertEquals(buildingResource.getDescription(), building.getDescription());
    }

    @Test
    @DisplayName("convertToResource Building - Mapper")
    @LoadCustomData
    @Order(2)
    public void convertToResourceTest(){
        Optional<BuildingResource> buildingResource = buildingMapper.convertToResource(Optional.of(building));
        if(buildingResource.isPresent()) {
            Assert.assertEquals(buildingResource.get().getName(), building.getName());
            Assert.assertEquals(buildingResource.get().getDescription(), building.getDescription());
        }
    }

    @Test
    @DisplayName("convertToSetResources Building - Mapper")
    @Order(3)
    public void convertToSetResourcesTest(){
        Set<Building> entities = new HashSet<>();
        Project project = new Project();
        project.setTitle("Project main");
        project.setId(1l);
        Building building = new Building();
        building.setId(1l);
        building.setName("Building Name");
        building.setDescription("Description building 1");
        building.setProject(project);
        entities.add(building);
        Set<BuildingResource> buildingResourceSet =
                buildingMapper.convertToSetResources(entities);
        Assert.assertEquals("Expected 1",1, buildingResourceSet.size());
    }

    @Test
    @DisplayName("convertToResources Building - Mapper")
    @Order(4)
    public void convertToResourcesTest(){
        List<Building> entities = new ArrayList<>();
        Project project = new Project();
        project.setTitle("Project main");
        project.setId(1l);
        Building building = new Building();
        building.setId(1l);
        building.setName("Building Name");
        building.setDescription("Description building 1");
        building.setProject(project);
        entities.add(building);
        List<BuildingResource> buildingResourceSet =
                buildingMapper.convertToResources(entities);
        Assert.assertEquals("Expected 1",1, buildingResourceSet.size());
    }

}