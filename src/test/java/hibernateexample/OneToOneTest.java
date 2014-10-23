package hibernateexample;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.onetomany.twoway.Owner;
import org.yyf.hibernateexample.domain.onetomany.twoway.Pet;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.domain.onetoone.twoway.Wife;
import org.yyf.hibernateexample.util.HibernateUtil;

/**
 * Created by yeyf on 2014-5-5.
 */
public class OneToOneTest {
    @Test
    public void insertTest(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

         Husband husband = (Husband)session.get(Husband.class, 1L);

        transaction.commit();
        session.close();
        Wife wife = husband.getWife();
        System.out.println(wife.getID());
//        System.out.println(husband.getWife());
    }
}
