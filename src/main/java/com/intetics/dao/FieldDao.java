package com.intetics.dao;

import com.intetics.bean.EntityName;
import com.intetics.bean.Field;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class FieldDao extends BaseDao<Field> {

    @Autowired
    public FieldDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Field getFieldByNameAndEntityName (String name, EntityName entityName)
    {
        Session session = currentSession();
        Field result;

        Criteria criteria = session.createCriteria(Field.class);
        Criterion nameCriterion = Restrictions.eq("name", name);
        Criterion entityNameCriterion = Restrictions.eq("entityName", entityName);
        LogicalExpression andExp = Restrictions.and(nameCriterion, entityNameCriterion);
        criteria.add(andExp);

        result = (Field) criteria.uniqueResult();

        return result;
    }
}
