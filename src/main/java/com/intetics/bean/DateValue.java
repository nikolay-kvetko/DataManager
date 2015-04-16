package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

@Entity
@DiscriminatorValue("DATE")
public class DateValue extends FieldValue {

    @Column(name = "date_value")
    private Date dateValue;

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }
}
