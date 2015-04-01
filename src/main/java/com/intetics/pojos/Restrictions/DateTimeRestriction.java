package com.intetics.pojos.restrictions;

import com.intetics.pojos.Field;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * created: 01.04.2015 12:21
 *
 * @author a.chervyakovsky
 */

@Entity
public class DateTimeRestriction {

    @Id
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "field")
    )
    @GeneratedValue(generator = "generator")
    private Long dateTimeRestrictionId;

    @Column(nullable = false)
    private String type;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Field field;

    public Long getDateTimeRestrictionId() {
        return dateTimeRestrictionId;
    }

    public void setDateTimeRestrictionId(Long dateTimeRestrictionId) {
        this.dateTimeRestrictionId = dateTimeRestrictionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
