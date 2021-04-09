package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.resources.ProjectResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the mapper {@link ProjectMapper}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
@Component("projectMapper")
public class ProjectMapper implements BaseMapper<Project, ProjectResource>{

    @Override
    public Project convertToEntity(ProjectResource r) {
        return convertToEntity(r, new Project());
    }

    private Project convertToEntity(ProjectResource r, Project project) {
        project.setDescription(r.getDescription());
        project.setTitle(r.getTitle());
        return project;
    }

    @Override
    public Project convertUpdateToEntity(ProjectResource r, Project project) {
        return convertToEntity(r, project);
    }

    @Override
    public Optional<ProjectResource> convertToResource(Optional<Project> entity){
        if(entity.isPresent()){
            Project project = entity.get();
            ProjectResource projectResource = new ProjectResource();
            projectResource.setDescription(project.getDescription());
            projectResource.setTitle(project.getTitle());
            projectResource.setProjectId(project.getId());
            return Optional.of(projectResource);
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectResource> convertToResources(List<Project> entities) {
        List<ProjectResource> projectResourceList = entities.stream()
                .map(entity -> {
                    ProjectResource projectResource = new ProjectResource();
                    projectResource.setProjectId(entity.getId());
                    projectResource.setTitle(entity.getTitle());
                    projectResource.setDescription(entity.getDescription());
                    return projectResource;
                })
                .collect(Collectors.toList());
        return projectResourceList;
    }

    @Override
    public Set<ProjectResource> convertToSetResources(Set<Project> entities) {
        Set<ProjectResource> projectResourceList = entities.stream()
                .map(entity -> {
                    ProjectResource projectResource = new ProjectResource();
                    projectResource.setProjectId(entity.getId());
                    projectResource.setTitle(entity.getTitle());
                    projectResource.setDescription(entity.getDescription());
                    return projectResource;
                })
                .collect(Collectors.toSet());
        return projectResourceList;
    }


}
