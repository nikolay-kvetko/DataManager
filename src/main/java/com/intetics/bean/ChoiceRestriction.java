package com.intetics.bean;

import javax.persistence.*;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Entity
public class ChoiceRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceRestrictionId;

    @Column
    private String label;

    @Column
    private String value;

    @Column
    private String position;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    public Long getChoiceRestrictionId() {
        return choiceRestrictionId;
    }

    public void setChoiceRestrictionId(Long choiceRestrictionId) {
        this.choiceRestrictionId = choiceRestrictionId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
