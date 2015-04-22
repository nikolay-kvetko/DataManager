package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "number_field")
@PrimaryKeyJoinColumn(name="field_id")
public class NumberField extends Field {

    public NumberField() {
        super(ValueType.NUMBER);
    }

    @Column(name = "min_value")
    private Integer minValue;

    @Column(name = "max_value")
    private Integer maxValue;

    @Column(name = "decimal")
    private Integer numberDecimal;

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getNumberDecimal() {
        return numberDecimal;
    }

    public void setNumberDecimal(Integer numberDecimal) {
        this.numberDecimal = numberDecimal;
    }
}
