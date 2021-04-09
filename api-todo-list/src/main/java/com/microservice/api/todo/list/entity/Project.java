package com.microservice.api.todo.list.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Entity Class for the Project {@link Project}.
 *
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Entity
@Table(name = "project")
public class Project extends BaseEntity{

    @Column(length = 32)
    private String title;

    @Column(length = 128)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @JsonBackReference
    private Set<Building> buildingSet;

    @Override
    public int onHashCode(int result) {
        final int prime = 31;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean onEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return 	Objects.equals(title, project.getTitle())
                && Objects.equals(description, project.getDescription());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Building> getBuildingSet() {
        return buildingSet;
    }

    public void setBuildingSet(Set<Building> buildingSet) {
        this.buildingSet = buildingSet;
    }
}
