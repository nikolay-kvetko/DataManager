package com.intetics.pojos.values;

import com.intetics.pojos.EntityValue;
import com.intetics.pojos.Field;

import javax.persistence.*;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Entity
public class StringValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stringValuesId;

    @Column(nullable = false)//maybe not required
    private String value;

    @ManyToOne
    @JoinColumn(name = "entityValueId")
    private EntityValue entityValue;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    public Long getStringValuesId() {
        return stringValuesId;
    }

    public void setStringValuesId(Long stringValuesId) {
        this.stringValuesId = stringValuesId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
