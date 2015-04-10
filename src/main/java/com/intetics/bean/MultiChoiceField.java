package com.intetics.bean;

import javax.persistence.*;
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

    public MultiChoiceField() {
        super(ValueType.MULTI_CHOICE);
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
