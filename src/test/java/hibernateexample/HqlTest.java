package hibernateexample;

import org.hibernate.*;
import org.junit.Test;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.domain.onetoone.twoway.HusbandMock;
import org.yyf.hibernateexample.util.HibernateUtil;

import java.util.List;
import java.util.Map;
import org.hibernate.criterion.*;
/**
 * Created by yeyf on 2014-5-6.
 */
public class HqlTest {
    @Test
    public void test() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createQuery("select w.age,w.name from Wife w").list();


        transaction.commit();
        session.close();
        System.out.println(list);
        for (Object[] row : list) {
            for (Object obj : row) {
                System.out.println(obj);
            }
        }
    }

    @Test
    public void testList() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<List> list = session.createQuery("select new List(w.age,w.name) from Wife w").list();

        transaction.commit();
        session.close();
        //获取
        for (List list1 : list) {
            for (Object o : list1) {
                System.out.print(o + " ");
            }
            System.out.println("\n");

        }
    }

    @Test
    public void testMap() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Map> list = session.createQuery("select new map(w.age as age ,w.name as name) from Wife w").list();

        transaction.commit();
        session.close();
        //获取
        for (Map map : list) {
            System.out.println(map);
        }
    }
    @Test
    public void testCriteria() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Husband.class);
        criteria.add(Restrictions.eq("name","sadfhn"));
        criteria.setFetchMode("wife", FetchMode.JOIN);
//        criteria.add(Restrictions.eq("wife.name","妞妞"));
        List<Husband> list = criteria.list();

        transaction.commit();
        session.close();
        //获取
        for (Husband husband : list) {
            System.out.println(husband);
        }
    }
    @Test
    public void testObject() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<HusbandMock> list = session.createQuery("select " +
                "new org.yyf.hibernateexample.domain.onetoone.twoway.HusbandMock(h.id as id ,h.name as name,h.age as age,h.wife.id as wifeId) from Husband h").list();

        transaction.commit();
        session.close();
        //获取
        for (HusbandMock husbandMock : list) {
            System.out.println(husbandMock);
        }
    }
    @Test
         public void testObject2() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<HusbandMock> list = session.createQuery("select " +
                " new org.yyf.hibernateexample.domain.onetoone.twoway.HusbandMock(h.id as id ,h.name as name,h.age as age,h.wife.id as wifeId) from Husband h where h.wife.name='太婆啊' ").list();

        transaction.commit();
        session.close();
        //获取
        for (HusbandMock husbandMock : list) {
            System.out.println(husbandMock);
        }
    }
    @Test
    public void testJoin() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("select  pet  from Owner o left join o.pets as pet where o.id=1 ").list();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        session.close();

    }
    @Test
    public void testJoin2() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("select  pet  from Owner o left join o.pets as pet where o.id=3 ").list();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        session.close();

    }
    @Test
    public void testDelete() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete Pet p where p.id=1 ").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Test
    public void testUpdate() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("update Pet p set p.age=100 , p.name='小米' where p.id=2 ").executeUpdate();

        transaction.commit();
        session.close();

    }
    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Husband husband = new Husband();
        husband.setAge(1011);
        husband.setName("xiaominggnaaaaa");
        session.saveOrUpdate(husband);
        transaction.commit();
        session.close();
        System.out.println(husband.getID());
    }
    @Test
    public void testQuery() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Husband husband = (Husband) session.get(Husband.class, 1L);
        transaction.commit();
        session.close();
        System.out.println(husband);
    }
    @Test
    public void testQueryByHql() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
//        List<Husband> list = session.createQuery("from Husband h  join fetch h.wife where h.id =1").list();
//        Husband husband = (Husband) session.createQuery("from Husband h  join fetch h.wife where h.id =1").uniqueResult();
        Husband husband = (Husband) session.createQuery("from Husband h  fetch all properties  where h.wife.name='太婆啊'").uniqueResult();
//        Long count =(Long)session.createQuery("select count(*) from Husband h   left join fetch h.wife w where w.name='太婆啊'").uniqueResult();
//        Husband husband = (Husband) session.createQuery("from Husband h where h.id =1").uniqueResult();

        transaction.commit();

        session.close();
//        System.out.println(husband);
        System.out.println(husband.getWife());
    }

    @Test
    public void testQueryPage() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
       List<Husband> list = session.createQuery("from Husband h left join fetch h.wife w where w.name='太婆啊'").list();
        String hql ="select count(*)  from Husband h left join fetch h.wife w where h.name ='sadfhn' ";
        Long count = (Long) session.createQuery(hql).uniqueResult();
        transaction.commit();

        session.close();
        System.out.println(list);
    }
    @Test
    public void hqlWithParameterTest() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
//        List<Husband> list = session.createQuery("from Husband h   left join fetch  h.wife w where w.name=:name").setParameter("name", "太婆啊").list();
//        List<Husband> list = session.createQuery("from Husband h  where h.name=:name").setParameter("name", "xiaominggnaaaaa").list();
        Query query = session.createQuery("from Husband h fetch all properties ");
        List list = query.list();
        Long count = (Long) session.createQuery("select count(w) from Husband h left join  h.wife w where w.name ='妞妞' ").uniqueResult();
        transaction.commit();

        session.close();
        System.out.println(count);
        System.out.println(list);
    }
}
