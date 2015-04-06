package com.intetics.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 05.04.2015.
 */

@Repository
public class TextStringRestrictionDao extends BaseDao<TextStringRestrictionDao> {

    @Autowired
    public TextStringRestrictionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
