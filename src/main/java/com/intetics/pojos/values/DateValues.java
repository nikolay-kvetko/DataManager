package com.intetics.pojos.values;

import com.intetics.pojos.EntityValue;
import com.intetics.pojos.Field;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Entity
public class DateValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateValuesId;

    @Column(nullable = false)//maybe not required
    @Temporal(TemporalType.TIMESTAMP)
    private Date value;

    @ManyToOne
    @JoinColumn(name = "entityValueId")
    private EntityValue entityValue;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    public Long getDateValuesId() {
        return dateValuesId;
    }

    public void setDateValuesId(Long dateValuesId) {
        this.dateValuesId = dateValuesId;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
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
