package com.microservice.api.todo.list.service;

import com.microservice.api.todo.list.entity.BaseEntity;
import com.microservice.api.todo.list.resources.BaseResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interface for the service{@link CrudService}.
 *
 * <p>This is a interface for Services. . .</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
public interface CrudService <ModelObjectType extends BaseEntity, KeyType extends Serializable, KeyResource extends BaseResource>{

    /**
     * Makes <code>entity</code> persistent.
     *
     * @param entity the entity to be persisted.
     *
     * @return the generated new persistent entity.
     */
    ModelObjectType create(ModelObjectType entity);

    /**
     * Updates the entity data into the data store.
     *
     * @param entity the entity to be updated.
     */
    void update(ModelObjectType entity);

    /**
     * Removes the entity from the data stores
     *
     * @param entity the entity to be removed.
     */
    void delete(ModelObjectType entity);

    /**
     * Searches the data store for the entity identified by <code>entityId</code>.
     *
     * @param entityId the primary identifier of the entity being searched.
     *
     * @return the entity identified by <code>entityId</code> or <code>null</code>
     *         if no entity was found.
     */
    Optional<ModelObjectType> findById(KeyType entityId);

    /**
     * Searches the data store for the entity identified by <code>entityId</code>.
     *
     * @param entityIds the primary identifier of the entity being searched.
     *
     * @return the entity identified by <code>entityIds</code> or <code>null</code>
     *         if no entity was found.
     */
    List<ModelObjectType> findAllById(List<KeyType> entityIds);

    /**
     * Returns all the entities of the given Service Class.
     *
     * @return the collection of entities.
     */
    List<ModelObjectType> getAll(CrudRepository crudRepository);

    JpaRepository<ModelObjectType, KeyType> getCrudRepository();

}
