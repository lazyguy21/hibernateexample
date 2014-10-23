package hibernateexample;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.EnumIncludedDomain;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.domain.onetoone.twoway.Wife;
import org.yyf.hibernateexample.util.HibernateUtil;

/**
 * Created by yeyf on 2014-5-5.
 */
public class EnumTest {
    @Test
    public void test(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        EnumIncludedDomain domain = new EnumIncludedDomain();
        domain.setId(1L);
        domain.setEnumColumn(org.yyf.hibernateexample.domain.EnumTest.Blue);
        session.save(domain);
        transaction.commit();
        session.close();
    }
    @Test
    public void queryTest(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        EnumIncludedDomain domain = (EnumIncludedDomain) session.get(EnumIncludedDomain.class, 10L);

        transaction.commit();
        session.close();
        System.out.println(domain);
    }


}
