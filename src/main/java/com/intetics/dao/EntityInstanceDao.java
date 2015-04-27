package com.intetics.dao;

import com.intetics.bean.EntityInstance;
import com.intetics.bean.EntitySchema;

import java.util.List;

/**
 * Operates over {@link com.intetics.bean.EntityInstance}
 */
public interface EntityInstanceDao {

    void saveOrUpdate(EntityInstance entityInstance);

    List<EntityInstance> getEntityInstancesByEntitySchema (EntitySchema entitySchema);

    void delete(EntityInstance entityInstance);

    EntityInstance getEntityInstance(Long id);
}
