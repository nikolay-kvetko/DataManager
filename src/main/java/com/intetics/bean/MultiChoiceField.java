package com.intetics.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */
@Entity
@Table(name = "multi_choice_field")
public class MultiChoiceField extends Field {
    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="multi_choice_field_id")
    private List<Choice> choices;

    @Column(name = "multi_choice_type")
    private String choiceType;

    public MultiChoiceField() {
        super(ValueType.MULTI_CHOICE);
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }
}
