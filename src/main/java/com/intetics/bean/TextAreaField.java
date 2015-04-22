package com.intetics.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "text_area")
@PrimaryKeyJoinColumn(name="field_id")
public class TextAreaField extends Field {

    public TextAreaField(){
        super(ValueType.TEXT_AREA);
    }

    @Column(name = "count_line")
    private Integer countLine;

    public Integer getCountLine() {
        return countLine;
    }

    public void setCountLine(Integer countLine) {
        this.countLine = countLine;
    }
}
