package com.intetics.bean;

import javax.persistence.*;
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
}
