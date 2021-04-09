package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.mappers.BuildingMapper;
import com.microservice.api.todo.list.mappers.TaskMapper;
import com.microservice.api.todo.list.resources.BuildingResource;
import com.microservice.api.todo.list.resources.TaskResource;
import com.microservice.api.todo.list.service.BuildingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * Class for the controller {@link BuildingController}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@RestController
@RequestMapping("building")
@OpenAPIDefinition(info = @Info(
        title = "OpenAPIDefinition BuildingController",
        version = "1",
        description = "BuildingController"))
public class BuildingController extends AbstractBasicCrudController<Building, Long, BuildingResource> {

    private BuildingService buildingService;
    private BuildingMapper buildingMapper;
    private TaskMapper taskMapper;

    public BuildingController(BuildingService buildingService, BuildingMapper buildingMapper, TaskMapper taskMapper){
        this.buildingService = buildingService;
        this.buildingMapper = buildingMapper;
        this.taskMapper = taskMapper;
    }

    @PostConstruct
    public void initialize() {
        setService(buildingService);
        setBaseMapper(buildingMapper);
    }

    /**
     *
     * @return buildings by project
     */
    @GetMapping("/project/{id}")
    @Operation(
            summary = "Get All",
            description = "Get All entities",
            responses = {
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public List<BuildingResource> listBuildingsByProject(@Parameter(required = true, in = ParameterIn.PATH,
            description = "The id for the entity",
            name = "id" ) @PathVariable("id") long id) {
        return buildingMapper.convertToResources(buildingService.listBuildingsByProject(id));
    }

    /**
     *
     * @return tasks by building
     */
    @GetMapping("/tasks/{id}")
    @Operation(
            summary = "Get All",
            description = "Get All entities",
            responses = {
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public Set<TaskResource> listTasksByBuilding(@Parameter(required = true, in = ParameterIn.PATH,
            description = "The id for the entity",
            name = "id" ) @PathVariable("id") long id) {
        return taskMapper.convertToSetResources(buildingService.listTasksByBuilding(id));
    }

}



