package com.microservice.api.todo.list.service;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.BuildingResource;

import java.util.List;
import java.util.Set;

/**
 * Class for services {@link BuildingService}.
 *
 * <p>Services for the bussiness logic of the Model: Building</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
public interface BuildingService extends CrudService<Building, Long, BuildingResource> {

    /**
     *
     * @param entityProjectId
     * @return List of projects for given projectId
     */
    List<Building> listBuildingsByProject(long entityProjectId);

    /**
     *
     * @param entityBuildingId
     * @return Set of tasks related to this building
     */
    Set<Task> listTasksByBuilding(long entityBuildingId);
}
