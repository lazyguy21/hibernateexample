package hibernateexample;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.onetomany.twoway.Owner;
import org.yyf.hibernateexample.domain.onetomany.twoway.Pet;
import org.yyf.hibernateexample.util.HibernateUtil;


/**
 *
 *@author yeyf
 *@date 2013��12��13��
 */
public class ManyToOneTest {
@Test
public void insertTest(){
	Session session = HibernateUtil.getSession();
	Transaction transaction = session.beginTransaction();
	Owner owner = new Owner();
	owner.setName("yyf阿斯顿飞");
	Pet cat = new Pet();
	cat.setName("cat");
	Pet dog = new Pet();
	dog.setName("dog");
	transaction.commit();
	session.close();
	
}
    @Test
    public void query(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("from Owner left join fetch all properties ");


        transaction.commit();
        session.close();
    }
}

