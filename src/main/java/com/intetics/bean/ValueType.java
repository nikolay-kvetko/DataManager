package com.intetics.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * todo[a.chervyakovsky] leave meaningful description here.
 */
public enum ValueType {
    STRING {
        public FieldValue newValue(List<String> values, Field field) {
            StringValue value = new StringValue();

            if(values != null) {
                value.setValue(values.get(0));
            }

            return value;
        }
    },
    TEXT_AREA{
        public FieldValue newValue(List<String> values, Field field){
            TextAreaValue value = new TextAreaValue();

            if(values != null) {
                value.setTextAreaValue(values.get(0));
            }

            return value;
        }
    },
    MULTI_CHOICE {
        public FieldValue newValue(List<String> values, Field field) {
            ArrayList<Choice> choices = new ArrayList<Choice>();
            MultiChoiceValue value = new MultiChoiceValue();

            if (values != null) {
                List<Choice> choiceList = ((MultiChoiceField) field).getChoices();

                for (Choice choice : choiceList) {
                    if (values.contains(choice.getId().toString())) {
                        choices.add(choice);
                    }
                }
            }
            value.setChoices(choices);

            return value;
        }
    },
    NUMBER{
        public FieldValue newValue(List<String> values, Field field) {
            NumberValue value = new NumberValue();

            if(values != null) {
                value.setNumberValue(Double.parseDouble(values.get(0)));
            }

            return value;
        }
    },
    DATE {
        public FieldValue newValue(List<String> values, Field field) {
            DateValue value = new DateValue();
            DateFormat format = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");

            if(values != null) {
                Date date;

                try {
                    date = format.parse(values.get(0));
                    value.setDateValue(date);
                } catch (ParseException e) {
                    //TODO: LOGGER
                }
            }

            return value;
        }
    },
    LOOK_UP{
        public FieldValue newValue(List<String> values, Field field) {
            return null;
        }
    },
    IMAGE{
        public FieldValue newValue(List<String> values, Field field) {
            return null;
        }
    };

    public abstract FieldValue newValue(List<String> values, Field field);
}
