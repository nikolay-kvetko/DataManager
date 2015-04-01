package com.intetics.pojos;

import com.intetics.pojos.restrictions.DateTimeRestriction;
import com.intetics.pojos.restrictions.NumberRestriction;
import com.intetics.pojos.restrictions.TextAreaRestriction;
import com.intetics.pojos.restrictions.TextStringRestriction;
import com.intetics.pojos.values.DateValues;
import com.intetics.pojos.values.NumberValues;
import com.intetics.pojos.values.StringValues;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 31.03.2015.
 */

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column
    private Boolean required;

    @ManyToOne
    @JoinColumn(name = "entityNameId")
    private EntityName entityName;

    //Restrictions of fields
    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private TextStringRestriction textStringRestriction;

    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private TextAreaRestriction textAreaRestriction;

    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private NumberRestriction numberRestriction;

    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private DateTimeRestriction dateTimeRestriction;

    //Values of fields
    @OneToMany(mappedBy = "field", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<StringValues> stringValues = new HashSet<StringValues>();

    @OneToMany(mappedBy = "field", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<NumberValues> numberValues = new HashSet<NumberValues>();

    @OneToMany(mappedBy = "field", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<DateValues> dateValues = new HashSet<DateValues>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public EntityName getEntityName() {
        return entityName;
    }

    public void setEntityName(EntityName entityName) {
        this.entityName = entityName;
    }

    public TextStringRestriction getTextStringRestriction() {
        return textStringRestriction;
    }

    public void setTextStringRestriction(TextStringRestriction textStringRestriction) {
        this.textStringRestriction = textStringRestriction;
    }

    public TextAreaRestriction getTextAreaRestriction() {
        return textAreaRestriction;
    }

    public void setTextAreaRestriction(TextAreaRestriction textAreaRestriction) {
        this.textAreaRestriction = textAreaRestriction;
    }

    public NumberRestriction getNumberRestriction() {
        return numberRestriction;
    }

    public void setNumberRestriction(NumberRestriction numberRestriction) {
        this.numberRestriction = numberRestriction;
    }

    public DateTimeRestriction getDateTimeRestriction() {
        return dateTimeRestriction;
    }

    public void setDateTimeRestriction(DateTimeRestriction dateTimeRestriction) {
        this.dateTimeRestriction = dateTimeRestriction;
    }

    public Set<StringValues> getStringValues() {
        return stringValues;
    }

    public void setStringValues(Set<StringValues> stringValues) {
        this.stringValues = stringValues;
    }

    public Set<NumberValues> getNumberValues() {
        return numberValues;
    }

    public void setNumberValues(Set<NumberValues> numberValues) {
        this.numberValues = numberValues;
    }

    public Set<DateValues> getDateValues() {
        return dateValues;
    }

    public void setDateValues(Set<DateValues> dateValues) {
        this.dateValues = dateValues;
    }
}
