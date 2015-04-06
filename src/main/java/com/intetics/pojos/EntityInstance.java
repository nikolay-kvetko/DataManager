package com.intetics.pojos;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Entity
public class EntityInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityInstanceId;

    @Column
    @Type(type = "date")
    private Date createDate;

    @Column
    @Type(type = "date")
    private Date lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "entityNameId")
    private EntityName entityName;

    //Values of fields
    @OneToMany(mappedBy = "entityInstance", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<FieldValue> fieldValues = new HashSet<FieldValue>();

    public Long getEntityInstanceId() {
        return entityInstanceId;
    }

    public void setEntityInstanceId(Long entityInstanceId) {
        this.entityInstanceId = entityInstanceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public EntityName getEntityName() {
        return entityName;
    }

    public void setEntityName(EntityName entityName) {
        this.entityName = entityName;
    }

    public Set<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
