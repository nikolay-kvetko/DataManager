package com.intetics.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * todo[a.chervyakovsky] leave meaningful description here.
 */
public enum ValueType {
    STRING {
        public FieldValue newValue(List<String> values, Field field) {
            StringValue value = new StringValue();
            value.setValue(values.get(0));
            return value;
        }
    },
    MULTI_CHOICE {
        public FieldValue newValue(List<String> values, Field field) {
            ArrayList<Choice> choices = new ArrayList<Choice>();
            for (Choice choice : choices) {
                if (values.contains(choice.getId().toString())) {
                    choices.add(choice);
                }
            }
            MultiChoiceValue value = new MultiChoiceValue();
            value.setChoices(choices);
            return value;
        }
    };

    public abstract FieldValue newValue(List<String> values, Field field);
}
