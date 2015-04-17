package com.intetics.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entity_schema")
public class EntitySchema {

    @Id
    @Column(name = "entity_schema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "entity_schema_id", nullable = false)
    private List<Field> fields;

    @OneToMany(mappedBy = "entitySchema")
    @org.hibernate.annotations.Cascade( {org.hibernate.annotations.CascadeType.DELETE})
    private List<EntityInstance> entityInstances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
