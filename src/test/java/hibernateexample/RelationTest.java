package hibernateexample;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.util.HibernateUtil;


/**
 *
 *@author yeyf
 *@date 2013Äê12ÔÂ12ÈÕ
 */
public class RelationTest {

	@Test
	public void test() {
		Session session = HibernateUtil.getSession();
		 Transaction transaction = session.beginTransaction();
		 	transaction.begin();
		 	session.get(Husband.class, 1L);
		 	transaction.commit();
		 	session.close();
	}

}

