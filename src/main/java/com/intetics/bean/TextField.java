package com.intetics.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "text_field")
@PrimaryKeyJoinColumn(name="field_id")
public class TextField extends Field {


    public TextField() {
        super(ValueType.STRING);
    }
}
