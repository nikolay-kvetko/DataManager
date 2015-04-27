package com.intetics.bean;

import javax.persistence.*;

/**
 * Abstract class for all fields
 */
@Entity
@Table(name = "field_value")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="value_type",
        discriminatorType= DiscriminatorType.STRING
)
public abstract class FieldValue {
    @Id
    @Column(name = "field_value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
