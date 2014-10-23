package org.yyf.hibernateexample.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 辅助类，延迟加载单例SessionFacory
 * @author yeyf
 * @date 2013年12月12日
 */
public class HibernateUtil {
	private static class SessionFactoryContainer {
		private static final SessionFactory sessionFactroy = new Configuration()
				.configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return SessionFactoryContainer.sessionFactroy;
	}
	public static Session getSession(){
		return getSessionFactory().openSession();
	}
	private HibernateUtil() {
	}
}
