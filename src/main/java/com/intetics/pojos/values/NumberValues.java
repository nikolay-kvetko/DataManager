package com.intetics.pojos.values;

import com.intetics.pojos.EntityValue;
import com.intetics.pojos.Field;

import javax.persistence.*;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Entity
public class NumberValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberValuesId;

    @Column(nullable = false)//maybe not required
    private Double value;

    @ManyToOne
    @JoinColumn(name = "entityValueId")
    private EntityValue entityValue;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    public Long getNumberValuesId() {
        return numberValuesId;
    }

    public void setNumberValuesId(Long numberValuesId) {
        this.numberValuesId = numberValuesId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public EntityValue getEntityValue() {
        return entityValue;
    }

    public void setEntityValue(EntityValue entityValue) {
        this.entityValue = entityValue;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
