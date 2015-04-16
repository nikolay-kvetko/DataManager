package com.intetics.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "image_field")
@PrimaryKeyJoinColumn(name = "field_id")
public class ImageField extends Field {
    public ImageField() {
        super(ValueType.IMAGE);
    }
}
