package hibernateexample;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.EnumIncludedDomain;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.util.HibernateUtil;

/**
 * Created by yeyf on 2014-5-9.
 */
public class Dictionary_FomulaTest {
    @Test
    public void test(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Husband husband = (Husband) session.get(Husband.class, 1L);
        transaction.commit();
        session.close();
        System.out.println(husband);
    }
}
