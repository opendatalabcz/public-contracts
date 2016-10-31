package hibernate;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateCriteriaCreator {

    @PersistenceContext
    private EntityManager entityManager;

    public Criteria createCriteria(Class clazz, String alias) {
        final HibernateEntityManager hibernateEntityManager = entityManager.unwrap(HibernateEntityManager.class);
        final Session session = hibernateEntityManager.getSession();
        return session.createCriteria(clazz, alias);
    }
}
