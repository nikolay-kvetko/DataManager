package com.intetics.dao;

import com.intetics.pojos.FieldValue;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Repository
public class FieldValueDao extends BaseDao<FieldValue> {

    @Autowired
    public FieldValueDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
