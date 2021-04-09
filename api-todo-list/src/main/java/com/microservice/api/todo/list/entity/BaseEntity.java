package com.microservice.api.todo.list.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstract super class for all the entities within the application.
 *
 * @author Steven Mendez
 * @version 1.0
 * @since 11/27/2020
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable, Comparable{

    /**
     * Logger object to use in model classes.
     */
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Id used as primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    /**
     *
     * @return a hash code value for this object.
     *
     * @see BaseEntity#hashCode()
     * @see Object#hashCode()
     */
    public abstract int onHashCode(int result);

    /**
     *
     * @return <tt>true</tt> if this object is considered to be equal to the
     * argument; <tt>false</tt> otherwise.
     *
     * @see BaseEntity#equals(Object)
	 * @see Object#equals(Object)
	 */
    public abstract boolean onEquals(Object o);

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BaseEntity) {
            BaseEntity e = (BaseEntity) o;
            Long thisId = getId();
            Long otherId = e.getId();
            if (thisId != null && otherId != null) {
                return thisId.equals(otherId) && getClass().equals(e.getClass());
            } else {
                return onEquals(e);
            }
        }
        return false;
    }

    /**
     * Basic wrapper for the {@link BaseEntity#onHashCode(int)} method that must be
     * implemented by all <tt>BasicEntity</tt> subclasses.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 6;
        return onHashCode(result);
    }



    @Override
    public int compareTo(Object o) {
        return this.getCreateDateTime().compareTo(((BaseEntity) o).getCreateDateTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
