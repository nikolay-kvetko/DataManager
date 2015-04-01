package com.intetics.pojos.restrictions;

import com.intetics.pojos.Field;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * created: 01.04.2015 12:22
 *
 * @author a.chervyakovsky
 */

@Entity
public class TextAreaRestriction {

    @Id
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "field")
    )
    @GeneratedValue(generator = "generator")
    private Long testAreaRestrictionId;

    @Column
    private Integer size;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Field field;

    public Long getTestAreaRestrictionId() {
        return testAreaRestrictionId;
    }

    public void setTestAreaRestrictionId(Long testAreaRestrictionId) {
        this.testAreaRestrictionId = testAreaRestrictionId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
