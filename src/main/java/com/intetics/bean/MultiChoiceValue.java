package com.intetics.bean;

import javax.persistence.*;
import java.util.List;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */
@Entity
@DiscriminatorValue("MULTI_CHOICE")
public class MultiChoiceValue extends FieldValue {
    @ManyToMany(
            targetEntity=Choice.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="choice_value",
            joinColumns=@JoinColumn(name="multi_choice_value_id"),
            inverseJoinColumns=@JoinColumn(name="choice_id")
    )
    private List<Choice> choices;

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
