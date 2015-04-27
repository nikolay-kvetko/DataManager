package com.intetics.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "gps_field")
@PrimaryKeyJoinColumn(name = "field_id")
public class GPSField extends Field {

    public GPSField() {
        super(ValueType.GPS);
    }
}
