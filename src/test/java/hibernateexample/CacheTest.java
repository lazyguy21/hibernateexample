package hibernateexample;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.cache.TUser;
import org.yyf.hibernateexample.util.HibernateUtil;


/**
 * Created by yeyf on 2014-5-16.
 */
public class CacheTest {
    @Test
    public void test() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
//        session.setCacheMode(CacheMode.NORMAL);
        TUser tuser1 = (TUser) session.get(TUser.class, 1);

        System.out.println(tuser1);
        transaction.commit();
        session.close();
        for (int i = 0; i < 10; i++) {
            Session session2 = HibernateUtil.getSession();
//            session2.setCacheMode(CacheMode.GET);
            Transaction transaction2 = session2.beginTransaction();
            TUser tuser = (TUser) session2.get(TUser.class, 1);

            transaction2.commit();
            session2.close();
            System.out.println(tuser);
        }
    }

    @Test
    public void testQueryCache() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < 3; i++) {
            session.createQuery("from Husband h where name=:name").setParameter("name",i+"").setCacheable(true).list();
        }


        transaction.commit();
        session.close();

    }
}
