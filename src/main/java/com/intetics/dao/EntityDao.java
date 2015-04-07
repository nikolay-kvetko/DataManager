package com.intetics.dao;

import com.intetics.bean.EntityName;

import java.util.List;

/**
 * todo[a.chervyakovsky] leave meaningful description here.
 */
public interface EntityDao {
    List<EntityName> getEntityList();
}
