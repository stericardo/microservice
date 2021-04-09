package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.entity.BaseEntity;
import com.microservice.api.todo.list.mappers.BaseMapper;
import com.microservice.api.todo.list.resources.BaseResource;
import com.microservice.api.todo.list.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Class for the management of the controllers {@link AbstractBasicCrudController}.
 *
 * <p>. . .</p>
 * <p>Base class for all controllers</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@CrossOrigin
public abstract class AbstractBasicCrudController <T extends BaseEntity, K extends Serializable, R extends BaseResource> {

    private CrudService<T, K, R> service;
    private BaseMapper<T, R> baseMapper;

    /**
     *
     * @param service for the polymorphism behavior
     */
    public void setService(CrudService<T, K, R> service) {
        this.service = service;
    }

    /**
     *
     * @param baseMapper implementation for specified entity-resource
     */
    public void setBaseMapper(BaseMapper<T, R> baseMapper){
        this.baseMapper = baseMapper;
    }

    /**
     *
     * @return
     */
    @GetMapping("/")
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
    public List<R> list() {
        return baseMapper.convertToResources(service.getAll(service.getCrudRepository()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "findById",
            description = "Get entity by Id",
            responses = {
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public R get(@Parameter(required = true, in = ParameterIn.PATH,
            description = "The id for the entity",
            name = "id" ) @PathVariable("id") K id) {
        Optional<R> r = baseMapper.convertToResource(service.findById(id));
        if( r.isPresent()) {
            return r.get();
        }
        return null;
    }

    /**
     *
     * @param r thanks to polymorphism and generics
     */
    @PostMapping("/")
    @Operation(
            summary = "Create",
            description = "Create new entity based on json request",
            responses = {
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public void create(@Parameter(required = true, description = "The body for the new entity")
                           @RequestBody R r) {
        service.create(baseMapper.convertToEntity(r));
    }

    /**
     *
     * @param r thanks to polymorphism and generics
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update",
            description = "Update new entity based on json request",
            responses = {
                    @ApiResponse(responseCode = "400",
                            description = "Bad request",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public ResponseEntity<String> update(@Parameter(required = true,
            description = "The body for the update entity")
            @RequestBody R r,
            @Parameter(required = true, in = ParameterIn.PATH,
                       description = "The id for the entity",
                       name = "id" )
            @PathVariable("id") K id) {

        Optional<T> optionalT = service.findById(id);
        if(optionalT.isPresent()) {
            service.update(baseMapper.convertUpdateToEntity(r, optionalT.get()));
            return new ResponseEntity<>("Fine", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Allowed ", HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param id of the object thanks to polymorphism and generics
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete",
            description = "Delete entity by Id",
            responses = {
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden resource",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {@Content(mediaType = "application/json")})
            })
    public void delete(@Parameter(required = true,
            description = "The id for the entity to delete",
            name = "id" )
            @PathVariable("id") K id) {
        Optional<T> entity = service.findById(id);
        if (entity.isPresent()) {
            service.delete(entity.get());
        }
    }

}
