package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.resources.BuildingResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the mapper {@link BuildingMapper}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
@Component("buildingMapper")
public class BuildingMapper implements BaseMapper<Building, BuildingResource>{

    @Override
    public Building convertToEntity(BuildingResource r) {
        Building building = new Building();
        return convertToEntity(r, building);
    }

    private Building convertToEntity(BuildingResource r, Building building) {
        building.setDescription(r.getDescription());
        building.setName(r.getName());
        Project project = new Project();
        project.setId(r.getIdProject());
        building.setProject(project);
        return building;
    }

    @Override
    public Building convertUpdateToEntity(BuildingResource r, Building building) {
        return convertToEntity(r, building);
    }

    @Override
    public Optional<BuildingResource> convertToResource(Optional<Building> entity){
        if(entity.isPresent()){
            Building building = entity.get();
            BuildingResource buildingResource = new BuildingResource();
            buildingResource.setDescription(building.getDescription());
            buildingResource.setName(building.getName());
            buildingResource.setIdProject(building.getProject().getId());
            buildingResource.setId(building.getId());
            buildingResource.setProjectName(building.getProject().getTitle());
            return Optional.of(buildingResource);
        }
        return Optional.empty();
    }

    @Override
    public List<BuildingResource> convertToResources(List<Building> entities) {
        List<BuildingResource> buildingResourceList = entities.stream()
                .map(entity -> {
                    BuildingResource buildingResource = new BuildingResource();
                    buildingResource.setIdProject(entity.getProject().getId());
                    buildingResource.setDescription(entity.getDescription());
                    buildingResource.setName(entity.getName());
                    buildingResource.setId(entity.getId());
                    buildingResource.setProjectName(entity.getProject().getTitle());
                    return buildingResource;
                })
                .collect(Collectors.toList());
        return buildingResourceList;
    }

    @Override
    public Set<BuildingResource> convertToSetResources(Set<Building> entities) {
        Set<BuildingResource> buildingResourceSet = entities.stream()
                .map(entity -> {
                    BuildingResource buildingResource = new BuildingResource();
                    if(entity.getProject() != null) {
                        buildingResource.setIdProject(entity.getProject().getId());
                        buildingResource.setProjectName(entity.getProject().getTitle());
                    }
                    buildingResource.setDescription(entity.getDescription());
                    buildingResource.setName(entity.getName());
                    buildingResource.setId(entity.getId());
                    return buildingResource;
                })
                .collect(Collectors.toSet());
        return buildingResourceSet;
    }

}
