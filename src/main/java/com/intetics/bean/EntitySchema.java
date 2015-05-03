package com.intetics.bean;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entity_schema")
@GroupSequence({
        NotEmpty.class,
        EntitySchema.class
})
public class EntitySchema {

    @GroupSequence({
            Size.class,
    })
    public interface MvcValidationSequence {}

    @Id
    @Column(name = "entity_schema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(groups = NotEmpty.class)
    @Size(min = 2, max = 30, groups = Size.class)
    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "create_date", updatable=false)
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "entity_schema_id", nullable = false)
    private List<Field> fields;

    @OneToMany(mappedBy = "entitySchema")
    @org.hibernate.annotations.Cascade( {org.hibernate.annotations.CascadeType.DELETE})
    private List<EntityInstance> entityInstances;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<EntityInstance> getEntityInstances() {
        return entityInstances;
    }

    public void setEntityInstances(List<EntityInstance> entityInstances) {
        this.entityInstances = entityInstances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntitySchema that = (EntitySchema) o;

        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
