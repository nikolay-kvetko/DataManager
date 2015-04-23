package com.intetics.bean;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");

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
            LookUpValue lookUpValue = new LookUpValue();

            if (values != null){
                LookUpField lookUpField = (LookUpField)field;
                Field selectField = null;//entitySchemaDao.getField(lookUpField.getLookUpFieldId());
                if (selectField.getValueType() == ValueType.STRING){
                    StringValue fieldValue = (StringValue) selectField.getFieldValues().get(Integer.parseInt(values.get(0)));
                    lookUpValue.setLookUpValue(fieldValue.getValue());
                } else if (selectField.getValueType() == ValueType.TEXT_AREA){
                    TextAreaValue fieldValue = (TextAreaValue) selectField.getFieldValues().get(Integer.parseInt(values.get(0)));
                    lookUpValue.setLookUpValue(fieldValue.getTextAreaValue());
                } else if (selectField.getValueType() == ValueType.NUMBER){
                    NumberValue fieldValue = (NumberValue) selectField.getFieldValues().get(Integer.parseInt(values.get(0)));
                    lookUpValue.setLookUpValue(String.valueOf(fieldValue.getNumberValue()));
                } else if (selectField.getValueType() == ValueType.IMAGE){
                    ImageValue fieldValue = (ImageValue) selectField.getFieldValues().get(Integer.parseInt(values.get(0)));
                    lookUpValue.setLookUpValue(fieldValue.getImage());
                } else if (selectField.getValueType() == ValueType.DATE){
                    DateValue fieldValue = (DateValue) selectField.getFieldValues().get(Integer.parseInt(values.get(0)));
                    lookUpValue.setLookUpValue(fieldValue.getDateValue().toString());
                }
            }

            return lookUpValue;
        }
    },
    IMAGE{
        public FieldValue newValue(List<String> values, Field field) {
            ImageValue value = new ImageValue();

            if(values != null) {
                try {
                    URL url = new URL(values.get(0));
                    InputStream inputStream = url.openStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                    byteArrayOutputStream.flush();
                    value.setImage(Base64.encode(byteArrayOutputStream.toByteArray()));
                    value.setImageUrl(values.get(0));
                } catch (MalformedURLException e) {
                } catch (IOException e) {
                } catch (Exception e){
                }
            }
            return value;
        }
    },
    GPS{
        public FieldValue newValue(List<String> values, Field field) {
            GPSValue gpsValue = new GPSValue();

            if (values != null){
                String[] position = values.get(0).split(",");
                gpsValue.setLatitudeValue(Double.valueOf(position[0]));
                gpsValue.setLongitudeValue(Double.valueOf(position[1]));
            }

            return gpsValue;
        }
    };

    public abstract FieldValue newValue(List<String> values, Field field);
}
