package com.intetics.bean;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "require")
    private boolean require;

    @CreationTimestamp
    @Column(name = "create_date", updatable=false)
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "count_look_up")
    private Long countLookUp;

    @OneToMany(mappedBy = "field")
    @org.hibernate.annotations.Cascade( {org.hibernate.annotations.CascadeType.DELETE})
    private List<FieldValue> fieldValues;

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

    public boolean getRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
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

    public List<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Long getCountLookUp() {
        return countLookUp;
    }

    public void setCountLookUp(Long countLookUp) {
        this.countLookUp = countLookUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (require != field.require) return false;
        if (fieldId != null ? !fieldId.equals(field.fieldId) : field.fieldId != null) return false;
        if (!name.equals(field.name)) return false;
        if (valueType != field.valueType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = valueType != null ? valueType.hashCode() : 0;
        result = 31 * result + (fieldId != null ? fieldId.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (require ? 1 : 0);
        return result;
    }
}
