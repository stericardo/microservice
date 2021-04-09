package com.microservice.api.todo.list.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Entity Class for the Building {@link Building}.
 *
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Entity
@Table(name = "building")
public class Building extends BaseEntity{

    @Column(length = 32)
    private String name;

    @Column(length = 128)
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_project", nullable = false)
    @JsonManagedReference
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    @JsonBackReference
    private Set<Task> taskSet;

    @Override
    public int onHashCode(int result) {
        final int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((project == null) ? 0 : project.hashCode());
        return result;
    }

    @Override
    public boolean onEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Building building = (Building) obj;
        return 	Objects.equals(name, building.getName())
                && Objects.equals(description, building.getDescription())
                && Objects.equals(project, building.getProject());
    }

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }
}
