package com.microservice.api.todo.list.resources;


/**
 * Class for resources {@link ProjectResource}.
 *
 * <p>Resources for the endpoints of the Model: Project</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
public class ProjectResource extends BaseResource{

    private String title;
    private String description;
    private long projectId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
