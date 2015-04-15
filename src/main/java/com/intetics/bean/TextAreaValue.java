package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("TEXT_AREA")
public class TextAreaValue extends FieldValue {
    @Column(name = "text_area_value")
    @Lob()
    private String textAreaValue;

    public String getTextAreaValue() {
        return textAreaValue;
    }

    public void setTextAreaValue(String textAreaValue) {
        this.textAreaValue = textAreaValue;
    }
}
