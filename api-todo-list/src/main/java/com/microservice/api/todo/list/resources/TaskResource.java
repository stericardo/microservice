package com.microservice.api.todo.list.resources;

/**
 * Class for resources {@link TaskResource}.
 *
 * <p>Resources for the endpoints of the Model: Task</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
public class TaskResource extends BaseResource{

    private long taskId;
    private String name;
    private String description;
    private long personId;
    private long buildingId;
    private String status;
    private String personName;
    private String buildingName;

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

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
