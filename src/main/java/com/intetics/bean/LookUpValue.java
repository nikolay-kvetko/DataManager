package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("LOOK_UP")
public class LookUpValue extends FieldValue{

    @Column(name = "look_up_value")
    @Lob()
    private String lookUpValue;

    @Column(name = "look_up_type")
    private String lookUpType;

    public String getLookUpValue() {
        return lookUpValue;
    }

    public void setLookUpValue(String lookUpValue) {
        this.lookUpValue = lookUpValue;
    }

    public String getLookUpType() {
        return lookUpType;
    }

    public void setLookUpType(String lookUpType) {
        this.lookUpType = lookUpType;
    }
}
