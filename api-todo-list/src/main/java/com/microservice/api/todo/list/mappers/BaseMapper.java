package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.entity.BaseEntity;
import com.microservice.api.todo.list.resources.BaseResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Class base mapper {@link BaseMapper}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
public interface BaseMapper <ModelObjectType extends BaseEntity, KeyResource extends BaseResource>{

    ModelObjectType convertToEntity(KeyResource r);

    ModelObjectType convertUpdateToEntity(KeyResource r, ModelObjectType t);

    Optional<KeyResource> convertToResource(Optional<ModelObjectType> entity);

    List<KeyResource> convertToResources(List<ModelObjectType> entities);

    Set<KeyResource> convertToSetResources(Set<ModelObjectType> entities);

}
