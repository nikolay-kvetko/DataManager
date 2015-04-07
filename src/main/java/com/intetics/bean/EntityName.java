package com.intetics.bean;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Entity
public class EntityName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityNameId;

    @Column
    private String name;

    @Column
    @Type(type = "date")
    private Date createDate;

    @Column
    @Type(type = "date")
    private Date lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "entityName", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<Field> fields = new HashSet<Field>();

    @OneToMany(mappedBy = "entityName", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<EntityInstance> entityInstance = new HashSet<EntityInstance>();

    public Long getEntityNameId() {
        return entityNameId;
    }

    public void setEntityNameId(Long entityNameId) {
        this.entityNameId = entityNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Set<EntityInstance> getEntityInstance() {
        return entityInstance;
    }

    public void setEntityInstance(Set<EntityInstance> entityInstance) {
        this.entityInstance = entityInstance;
    }
}
