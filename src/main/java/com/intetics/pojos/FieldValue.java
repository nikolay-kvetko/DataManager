package com.intetics.pojos;

import javax.persistence.*;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Entity
public class FieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldValuesId;

    @Column
    private String textValue;

    @Column
    private Double numberValue;

    @Column
    private Double choiceValue;

    @ManyToOne
    @JoinColumn(name = "entityInstanceId")
    private EntityInstance entityInstance;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    public Long getFieldValuesId() {
        return fieldValuesId;
    }

    public void setFieldValuesId(Long fieldValueId) {
        this.fieldValuesId = fieldValueId;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Double getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Double numberValue) {
        this.numberValue = numberValue;
    }

    public EntityInstance getEntityInstance() {
        return entityInstance;
    }

    public void setEntityInstance(EntityInstance entityInstance) {
        this.entityInstance = entityInstance;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Double getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(Double choiceValue) {
        this.choiceValue = choiceValue;
    }
}
