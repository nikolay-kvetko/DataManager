package com.intetics.bean;

import javax.persistence.*;

@Entity
@Table(name = "EntitySchema")
public class EntitySchema {

    @Id
    @Column(name = "EntitySchema_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entitySchemaId;

    @Column(name = "Name")
    private String name;

    public Long getEntitySchemaId() {
        return entitySchemaId;
    }

    public void setEntitySchemaId(Long entitySchemaId) {
        this.entitySchemaId = entitySchemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
