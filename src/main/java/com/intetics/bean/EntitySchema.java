package com.intetics.bean;

import com.intetics.validation.DuplicateEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "entity_schema")
@GroupSequence({
        NotEmpty.class,
        Size.class,
        EntitySchema.class
})
public class EntitySchema {

    @GroupSequence({
            DuplicateEntity.class
    })
    public interface MvcValidationSequence {}

    @Id
    @Column(name = "entity_schema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(groups = NotEmpty.class)
    @Size(min = 2, max = 30, groups = Size.class)
    @DuplicateEntity(groups = DuplicateEntity.class)
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "entity_schema_id", nullable = false)
    private List<Field> fields;

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
}
