package com.intetics.bean;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */
@Entity
@Table(name = "entity_instance")
public class EntityInstance {
    @Id
    @Column(name = "entity_instance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entity_schema_id")
    private EntitySchema entitySchema;

    @CreationTimestamp
    @Column(name = "create_date", updatable=false)
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="entity_instance_id")
    private List<FieldValue> values;

    public Long getId() {
        return id;
    }

    public List<FieldValue> getValues() {
        return values;
    }

    public void setValues(List<FieldValue> values) {
        this.values = values;
    }

    public void setEntitySchema(EntitySchema entitySchema) {
        this.entitySchema = entitySchema;
    }

    public EntitySchema getEntitySchema() {
        return entitySchema;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityInstance that = (EntityInstance) o;

        if (entitySchema != null ? !entitySchema.equals(that.entitySchema) : that.entitySchema != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entitySchema != null ? entitySchema.hashCode() : 0);
        return result;
    }
}
