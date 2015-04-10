package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */
@Entity
@DiscriminatorValue("STRING")
public class StringValue extends FieldValue {
    @Column(name = "string_value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
