package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.resources.ProjectResource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectMapperTest extends BaseIntegrationTestAdapter {

    @Autowired
    ProjectMapper projectMapper;

    @Test
    @DisplayName("convertToSetResources Task - Mapper")
    @Order(1)
    public void convertToSetResources(){
        Set<Project> projectSet = new HashSet<>();
        Project project = new Project();
        project.setTitle("Title");
        project.setId(1l);
        project.setDescription("Description");
        projectSet.add(project);
        Set<ProjectResource> projectResourceSet = projectMapper.convertToSetResources(projectSet);
        Assert.assertEquals("Expected 1",1, projectResourceSet.size());
    }

    @Test
    @DisplayName("convertToResources Task - Mapper")
    @Order(2)
    public void convertToResources(){
        List<Project> projectList = new ArrayList<>();
        Project project = new Project();
        project.setTitle("Title");
        project.setId(1l);
        project.setDescription("Description");
        projectList.add(project);
        List<ProjectResource> projectResourceList = projectMapper.convertToResources(projectList);
        Assert.assertEquals("Expected 1",1, projectResourceList.size());
    }

}
