package hibernateexample;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.util.HibernateUtil;

import java.util.List;

/**
 * Created by yeyf on 2014-5-11.
 */
public class CriteriaTest {
    @Test
    public void projectionTest(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

       Criteria criteria= session.createCriteria(Husband.class);
        criteria.addOrder(Order.asc("id"));
        criteria.add(Restrictions.eq("name", "xiaominggnaaaaa"));
        criteria.setProjection(Projections.rowCount());
        System.out.println(criteria.uniqueResult());


        criteria.setProjection(null);
        List list = criteria.list();
        System.out.println(list);
        transaction.commit();
        session.close();
    }
}
