package com.intetics.bean;

import javax.persistence.*;

@Entity
@Table(name = "field")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Field {

    @Transient
    private ValueType valueType;

    public Field(ValueType valueType) {
        this.valueType = valueType;
    }

    @Id
    @Column(name = "field_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @Column(name = "name")
    private String name;

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValueType getValueType() {
        return valueType;
    }
}
