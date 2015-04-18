package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "look_up_field")
@PrimaryKeyJoinColumn(name="field_id")
public class LookUpField extends Field{
    public LookUpField() {
        super(ValueType.LOOK_UP);
    }

    @Column(name = "look_up_entity_id")
    private Long lookUpEntityId;

    @Column(name = "look_up_field_id")
    private Long lookUpFieldId;

    public Long getLookUpFieldId() {
        return lookUpFieldId;
    }

    public void setLookUpFieldId(Long lookUpFieldId) {
        this.lookUpFieldId = lookUpFieldId;
    }

    public Long getLookUpEntityId() {
        return lookUpEntityId;
    }

    public void setLookUpEntityId(Long lookUpEntityId) {
        this.lookUpEntityId = lookUpEntityId;
    }
}
