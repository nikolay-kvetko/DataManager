package com.intetics.pojos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Кузнец on 05.04.2015.
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


    //restrictions of fields
    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private TextStringRestriction textStringRestriction;

    @OneToMany(mappedBy = "field", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<ChoiceRestriction> choiceRestrictions = new HashSet<ChoiceRestriction>();


    //Values of fields
    @OneToMany(mappedBy = "field", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<FieldValue> values = new HashSet<FieldValue>();

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

    public Set<FieldValue> getValues() {
        return values;
    }

    public void setValues(Set<FieldValue> values) {
        this.values = values;
    }

    public Set<ChoiceRestriction> getChoiceRestrictions() {
        return choiceRestrictions;
    }

    public void setChoiceRestrictions(Set<ChoiceRestriction> choiceRestrictions) {
        this.choiceRestrictions = choiceRestrictions;
    }
}
