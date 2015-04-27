package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Date class determines the completeness of the date display
 */

@Entity
@Table(name = "date_field")
@PrimaryKeyJoinColumn(name="field_id")
public class DateField extends Field {

    public DateField() {super(ValueType.DATE);}

    @Column(name = "full_date")
    private Boolean fullDate;

    public Boolean getFullDate() {
        return fullDate;
    }

    public void setFullDate(Boolean fullDate) {
        this.fullDate = fullDate;
    }
}
