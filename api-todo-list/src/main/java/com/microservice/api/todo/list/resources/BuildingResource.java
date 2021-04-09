package com.microservice.api.todo.list.resources;

/**
 * Class for resources {@link BuildingResource}.
 *
 * <p>Resources for the endpoints of the Model: Buiding</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
public class BuildingResource extends BaseResource{

    private String name;
    private String description;
    private long id;
    private long idProject;
    private String projectName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
