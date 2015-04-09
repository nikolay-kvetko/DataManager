package com.intetics.dao;

import com.intetics.bean.EntitySchema;

import java.util.List;

/**
 * Operates over {@link com.intetics.bean.EntitySchema}
 */
public interface EntitySchemaDao {

    List<EntitySchema> getEntitySchemaList();

    EntitySchema getEntitySchema(Long id);

    void saveOrUpdate(EntitySchema entitySchema);

    void delete(EntitySchema entitySchema);
}
