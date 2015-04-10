package com.intetics.dao;

import com.intetics.bean.Field;

import java.util.List;

/**
 * Created by s.berzhynsky on 10.04.2015.
 */
public interface EntityFieldDao {

    List<Field> getEntityFieldList(Long entitySchemaId);

    Field getField(long fieldId);
}
