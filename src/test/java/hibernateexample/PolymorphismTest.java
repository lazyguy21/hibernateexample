package hibernateexample;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.yyf.hibernateexample.domain.polymorphism.Creature;
import org.yyf.hibernateexample.domain.polymorphism.Dog;
import org.yyf.hibernateexample.domain.polymorphism.Human;
import org.yyf.hibernateexample.util.HibernateUtil;


/**
 *
 *@author yeyf
 *@date 2014年4月18日
 */
public class PolymorphismTest {
	@Test
	public void save(){
		Human creature = new Human();
		
//		creature.setId(2L);
		creature.setName("yyf");
		creature.setAge(10);
		creature.setHome("sichuan"); 	

		
//		Session session = HibernateUtil.getSession();
//		Transaction transaction = session.beginTransaction();
//		
//		session.saveOrUpdate((Creature)creature);
//		
//		transaction.commit();
//		session.close();
		
		
		Dog dog = new Dog();
		dog.setName("旺财");
		dog.setAge(3);
		dog.setBrood("小窝一号");
		Session session2 = HibernateUtil.getSession();
		Transaction transaction2 = session2.beginTransaction();
		
//		session2.saveOrUpdate((Creature)dog);
		session2.saveOrUpdate(dog);
		
		transaction2.commit();
		session2.close();
	}
}

