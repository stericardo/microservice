package com.microservice.api.todo.list.service.impl;

import com.microservice.api.todo.list.entity.BaseEntity;
import com.microservice.api.todo.list.resources.BaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Crudservice Impl base development of the CRUD application
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 */
public abstract class CrudServiceImpl<ModelObjectType extends BaseEntity, KeyType extends Serializable, KeyResource extends BaseResource> {

    protected final transient Logger logger = LoggerFactory.getLogger(getDomainClass());
    private JpaRepository<ModelObjectType, KeyType> repository;

    @SuppressWarnings("rawtypes")
    private Class domainClass;

    /**
     * This method is used to help with the generics
     * @return
     */
    protected Class<?> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<?>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    /**
     * This method will be called from Every Service
     *
     * @param repository
     */
    public void setCrudRepository(JpaRepository<ModelObjectType, KeyType> repository) {
        this.repository = repository;
    }

    public JpaRepository<ModelObjectType, KeyType> getCrudRepository() {
        return this.repository;
    }

    public List<ModelObjectType> getAll(CrudRepository crudRepository) {
        return (List<ModelObjectType>) crudRepository.findAll();
    }

    /**
     *
     * Get all method used thanks to repository within every service
     *
     * @return
     */
    public List<ModelObjectType> getAll() {
        return getAll(getCrudRepository());
    }

    /**
     * Based on Spring Data we are getting this information
     *
     * @param entityIds to get based on generics
     * @return list of the found objects
     */
    public List<ModelObjectType> findAllById(List<KeyType> entityIds) {
        return (List<ModelObjectType>) repository.findAllById(entityIds);
    }

    /**
     * Based on Spring Data we are getting this information
     *
     * @param entityId to get based on generics
     * @return found object
     */
    public Optional<ModelObjectType> findById(KeyType entityId) {
        Class<?> type = getDomainClass();
        try {
            return repository.findById(entityId);
        } catch (ObjectRetrievalFailureException e) {
            if (logger.isWarnEnabled()) {
                logger.warn(String.format("object instance of %s identified by %s not found in the database",
                        type.getName(), entityId));
            }
            return Optional.empty();
        }
    }

    /**
     * Save entity
     *
     * @param entity
     * @return created Entity
     */
    public ModelObjectType create(ModelObjectType entity) {
        return repository.save(entity);
    }

    /**
     * Delete entity
     *
     * @param entity to be deleted
     */
    public void delete(ModelObjectType entity) {
        repository.delete(entity);
    }

    /**
     *
     * @param entity to update based on generics
     */
    public void update(ModelObjectType entity) {
        repository.save(entity);
    }

}
