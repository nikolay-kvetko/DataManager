package com.intetics.bean;

import javax.persistence.Column;
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

    @Column(name = "size")
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
