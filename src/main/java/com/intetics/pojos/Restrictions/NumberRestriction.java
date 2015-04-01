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
public class NumberRestriction {

    @Id
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "field")
    )
    @GeneratedValue(generator = "generator")
    private Long numberRestrictionId;

    @Column
    private Double maxValue;

    @Column
    private Double minValue;

    @Column(nullable = false)
    private Integer numberDecimalPlaces;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Field field;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Integer getNumberDecimalPlaces() {
        return numberDecimalPlaces;
    }

    public void setNumberDecimalPlaces(Integer numberDecimalPlaces) {
        this.numberDecimalPlaces = numberDecimalPlaces;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Long getNumberRestrictionId() {
        return numberRestrictionId;
    }

    public void setNumberRestrictionId(Long numberRestrictionId) {
        this.numberRestrictionId = numberRestrictionId;
    }
}
