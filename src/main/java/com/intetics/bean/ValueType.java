package com.intetics.bean;

import java.util.List;

/**
 * todo[a.chervyakovsky] leave meaningful description here.
 */
public enum ValueType {
    STRING;

    public FieldValue newValue(List<String> values) {
        StringValue value = new StringValue();
        value.setValue(values.get(0));
        return value;
    }
}
