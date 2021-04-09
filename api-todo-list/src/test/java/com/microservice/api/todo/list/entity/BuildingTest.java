package com.microservice.api.todo.list.entity;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BuildingTest {

    @Test
    @DisplayName("Building Entity Test")
    @Order(1)
    public void buildingTest(){
        Map<Building, String> map = new HashMap<>();
        Project project = new Project();
        Building building = new Building();
        building.setId(1l);
        building.setName("Building Name");
        building.setDescription("Description building 1");
        building.setProject(project);
        map.put(building, "1");
        Building building2 = new Building();
        building2.setId(1l);
        building2.setName("Building Name");
        building2.setDescription("Description building 1");
        map.put(building2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected true", true,building.equals(building2));
    }

    @Test
    @DisplayName("Building Null Values Entity Test")
    @Order(2)
    public void buildingNullTest(){
        Map<Building, String> map = new HashMap<>();
        Building building = new Building();
        building.setId(1l);
        map.put(building, "1");
        building = new Building();
        building.setId(1l);
        map.put(building, "1");
        Assert.assertEquals("Expected 1", 1, map.size());
    }

    @Test
    @DisplayName("Building ID distinct Entity Test")
    @Order(1)
    public void buildingIdDiffTest(){
        Map<Building, String> map = new HashMap<>();
        Project project = new Project();
        Building building = new Building();
        building.setId(1l);
        building.setName("Building Name");
        building.setDescription("Description building 1");
        building.setProject(project);
        map.put(building, "1");
        Building building2 = new Building();
        building2.setId(1l);
        building2.setName("Building Name");
        building2.setDescription("Description building 1");
        map.put(building2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected true", true,building.equals(building2));
    }

}
