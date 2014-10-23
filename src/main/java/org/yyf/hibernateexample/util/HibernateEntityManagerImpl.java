//package org.yyf.hibernateexample.util;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Vector;
//
//import org.hibernate.Criteria;
//import org.hibernate.LockOptions;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.CriteriaSpecification;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Example;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.util.Assert;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.zznode.se.inmp.domain.basemodel.AssignedIdentifier;
//import com.zznode.se.inmp.domain.basemodel.CodeNameable;
//import com.zznode.se.inmp.domain.basemodel.Orderable;
//import com.zznode.se.inmp.domain.basemodel.SequenceIdentifier;
//import com.zznode.se.inmp.domain.basemodel.UnDeletable;
//import com.zznode.se.inmp.domain.commons.persistence.EntityManager;
//import com.zznode.se.inmp.domain.commons.persistence.IndexMapModel;
//import com.zznode.se.inmp.domain.commons.persistence.UnitOfWork;
//import com.zznode.se.inmp.domain.commons.persistence.instruction.impl.HibernatePersistenceInstructionVisitor;
//import com.zznode.se.inmp.tools.commons.Page;
//
///**
// *
// * @author taoping
// *
// */
//@SuppressWarnings("unchecked")
//public class HibernateEntityManagerImpl implements EntityManager, InitializingBean {
//    private static final Logger logger = LoggerFactory.getLogger(HibernateEntityManagerImpl.class);
//
//	private SessionFactory sessionFactory;
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	public Session getCurrentSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	private <T> Serializable convertId(Class<T> clazz, Serializable id) {
//		if (null == clazz || null == id) {
//			return id;
//		}
//		if (AssignedIdentifier.class.isAssignableFrom(clazz) && !String.class.isInstance(id)) {
//			return id.toString();
//		} else if (SequenceIdentifier.class.isAssignableFrom(clazz) && !Long.class.isInstance(id)) {
//			return Long.valueOf(id.toString());
//		}
//		return id;
//	}
//
//	@Override
//	public <T> T load(Class<T> clazz, Serializable id) {
//		return clazz.cast(getCurrentSession().load(clazz, convertId(clazz, id)));
//	}
//
//	@Override
//	public <T> T get(Class<T> clazz, Serializable id) {
//		return clazz.cast(getCurrentSession().get(clazz, convertId(clazz, id)));
//	}
//
//	@Override
//	public <T> void refresh(T entity) {
//		getCurrentSession().refresh(entity);
//	}
//
//	@Override
//	public <T> void refresh(T entity, LockOptions lockOptions) {
//		getCurrentSession().refresh(entity, lockOptions);
//	}
//
//	@Override
//	public <T> List<T> getAll(Class<T> clazz) {
//		if (null == clazz) {
//			return null;
//		}
//		Criteria criteria = getCurrentSession().createCriteria(clazz);
//		criteria.setCacheable(true);
//		if (UnDeletable.class.isAssignableFrom(clazz)) {
//			criteria.add(Restrictions.eq("isDeleted", Boolean.FALSE));
//		}
//		if (Orderable.class.isAssignableFrom(clazz)) {
//			criteria.addOrder(Order.asc("priority"));
//		}
//		if (CodeNameable.class.isAssignableFrom(clazz)) {
//			criteria.addOrder(Order.asc("name"));
//		}
//		return criteria.list();
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	public <T> Page<T> getPageByCriterion(Class<T> clazz, int currentPageNo, int pageSize, Order[] orders,
//			Criterion... criterions) {
//		Criteria criteria = createCriteria(clazz, criterions);
//		// 处理完参数中的criterions后需加上idDeleted，剃出已经被逻辑删除的数据
//		if (UnDeletable.class.isAssignableFrom(clazz)) {
//			criteria.add(Restrictions.eq("isDeleted", Boolean.FALSE));
//		}
//		long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//		int totalCount = (int) count;
//		if (totalCount == 0) {
//			return new Page(1L, 0L, pageSize, Collections.EMPTY_LIST);
//		}
//		// 计算count值后将Projection重置为null，否则查询不出具体数据
//		criteria.setProjection(null);
//		for (Order o : orders) {
//			criteria.addOrder(o);
//		}
//		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//		List<T> data = criteria.setFirstResult(Page.getStartOfPage(currentPageNo, pageSize)).setMaxResults(pageSize).list();
//		return new Page<T>(currentPageNo, totalCount, pageSize, data);
//	}
//
//	/**
//	 * 通过HQL计算总数 <h1>注意：使用HQL时需自己 注意逻辑删除条件(即 is_deleted 属性)</h1>
//	 *
//	 * @param hql
//	 *            只能接受以 （from XX）起头的HQL，以（select xxx）开头的HQL会出现语法错误
//	 * @return
//	 */
//	@Override
//	public Long getCountByHql(final String hql, Map<String, Object> queryParameters) {
//		Assert.notNull(hql, "hql cannot be null !");
//		String queryCountSql = "select count(*) " + hql;
//		Query query = getCurrentSession().createQuery(queryCountSql);
//		setParamtersToQuery(query, queryParameters);
//		return (Long) query.uniqueResult();
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Override
//	public Page getPageByHql(final String hql, Map<String, Object> queryParameters, int currentPageNo, int pageSize) {
//		long count = getCountByHql(hql, queryParameters);
//		int totalCount = (int) count;
//		if (totalCount == 0) {
//			return new Page(1L, 0L, pageSize, Collections.EMPTY_LIST);
//		}
//		Query query = getCurrentSession().createQuery(hql);
//		setParamtersToQuery(query, queryParameters);
//		List datas = query.setFirstResult(Page.getStartOfPage(currentPageNo, pageSize)).setMaxResults(pageSize).list();
//		return new Page(currentPageNo, totalCount, pageSize, datas);
//	}
//
//	private void setParamtersToQuery(Query query, Map<String, Object> queryParameters) {
//		Set<Map.Entry<String, Object>> entrySet = queryParameters.entrySet();
//		if (queryParameters != null && queryParameters.size() != 0) {
//			for (Map.Entry<String, Object> entry : entrySet) {
//				query.setParameter(entry.getKey(), entry.getValue());
//			}
//		}
//	}
//
//	/**
//	 * 根据Class类型 ，Criterion条件创建Criteria.
//	 *
//	 * @param clazz
//	 * @param criterions
//	 * @return
//	 */
//	private <T> Criteria createCriteria(Class<T> clazz, final Criterion... criterions) {
//
//		Criteria criteria = getCurrentSession().createCriteria(clazz);
//		criteria.setCacheable(true);
//		for (Criterion c : criterions) {
//			criteria.add(c);
//		}
//
//		return criteria;
//	}
//
//	@Override
//	public <T extends CodeNameable<?>> Map<String, String> getAllToMap(Class<T> clazz, IndexMapModel index) {
//		if (null == clazz || null == index) {
//			return null;
//		}
//		List<T> allEntry = null;
//		if (clazz.isEnum()) {
//			allEntry = new Vector<T>();
//			for (T entry : clazz.getEnumConstants()) {
//				allEntry.add(entry);
//			}
//		} else {
//			allEntry = getAll(clazz);
//		}
//		if (null == allEntry) {
//			return null;
//		}
//		Map<String, String> mapResult = new Hashtable<String, String>();
//		for (CodeNameable<?> entry : allEntry) {
//			if (IndexMapModel.NameId == index && null != entry.getName()) {
//				mapResult.put(entry.getName(), entry.getId().toString());
//			} else if (IndexMapModel.CodeId == index && null != entry.getCode()) {
//				mapResult.put(entry.getCode(), entry.getId().toString());
//			} else if (IndexMapModel.CodeName == index && null != entry.getName() && null != entry.getCode()) {
//				mapResult.put(entry.getCode(), entry.getCode());
//			} else if (IndexMapModel.IdName == index && null != entry.getName()) {
//				mapResult.put(entry.getId().toString(), entry.getName());
//			} else {
//				continue;
//			}
//		}
//		return mapResult;
//	}
//
//	@Override
//	public Serializable save(Object entity) {
//		return getCurrentSession().save(entity);
//	}
//
//	@Override
//	public Serializable[] save(Object[] entities) {
//		if (null == entities || entities.length == 0) {
//			return null;
//		}
//		List<Serializable> results = new ArrayList<Serializable>();
//		for (Object entry : entities) {
//			results.add(save(entry));
//		}
//		return results.toArray(new Serializable[] {});
//	}
//
//	@Override
//	public Serializable[] save(Collection<?> entities) {
//		return save(entities.toArray());
//	}
//
//	@Override
//	public void update(Object entity) {
//		getCurrentSession().update(entity);
//	}
//
//	@Override
//	public void update(Object[] entities) {
//		if (null == entities || entities.length == 0) {
//			return;
//		}
//		for (Object entry : entities) {
//			update(entry);
//		}
//	}
//
//	@Override
//	public void update(Collection<?> entities) {
//		update(entities.toArray());
//	}
//
//	@Override
//	public <T> T merge(Class<T> clazz, T entity) {
//		return clazz.cast(getCurrentSession().merge(entity));
//	}
//
//	@Override
//	public void saveOrUpdate(Object entity) {
//		getCurrentSession().saveOrUpdate(entity);
//	}
//
//	@Override
//	public void delete(Object entity) {
//		if (null == entity) {
//			return;
//		}
//		if (UnDeletable.class.isInstance(entity)) {
//			UnDeletable instance = UnDeletable.class.cast(entity);
//			instance.markedAsDeleted();
//			this.update(instance);
//			return;
//		}
//		getCurrentSession().delete(entity);
//	}
//
//	@Override
//	public void delete(Object[] entities) {
//		if (null == entities || entities.length == 0) {
//			return;
//		}
//		for (Object entry : entities) {
//			delete(entry);
//		}
//	}
//
//	@Override
//	public void delete(Collection<?> entities) {
//		delete(entities.toArray());
//	}
//
//	@Override
//	public <T> void deleteById(Class<T> clazz, Serializable id) {
//		if (null == clazz || null == id) {
//			return;
//		}
//		// Serializable newId = convertId(clazz, id);
//		if (UnDeletable.class.isAssignableFrom(clazz)) {
//			UnDeletable instance = UnDeletable.class.cast(this.get(clazz, id));
//			instance.markedAsDeleted();
//			this.update(instance);
//			return;
//		}
//		getCurrentSession().delete(load(clazz, id));
//	}
//
//	@Override
//	public <T> void deleteById(Class<T> clazz, Serializable[] ids) {
//		if (null == ids || ids.length == 0) {
//			return;
//		}
//		for (Serializable entry : ids) {
//			deleteById(clazz, entry);
//		}
//	}
//
//	@Override
//	public <T> void deleteById(Class<T> clazz, Collection<Serializable> ids) {
//		if (null == ids || ids.size() == 0) {
//			return;
//		}
//		for (Serializable entry : ids) {
//			deleteById(clazz, entry);
//		}
//	}
//
//	@Override
//	public void execute(UnitOfWork unitOfWork) {
//		if (null != unitOfWork) {
//			unitOfWork.execute(new HibernatePersistenceInstructionVisitor(getCurrentSession()));
//		}
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		if (null == this.sessionFactory) {
//			logger.warn("Property 'sessionFactory' is null");
//		}
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public <T> List<T> findByExample(T example) {
//		Assert.notNull(example, "Example entity must not be null");
//		Criteria criteria = this.getCurrentSession().createCriteria(example.getClass());
//		criteria.add(Example.create(example));
//		return criteria.list();
//	}
//
//	@Override
//	public boolean validateCode(Class<? extends CodeNameable<? extends Serializable>> domainType, Serializable entityId,
//			String entityCode) {
//		Assert.notNull(domainType, "Domain type must not be null.");
//		Assert.isTrue(StringUtils.hasText(entityCode), "Entity code must not be empty.");
//		logger.debug("Params - domainType : " + domainType + "\tentityCode : " + entityCode + "\tentityId : " + entityId);
//		Criteria criteria = this.getCurrentSession().createCriteria(domainType);
//		criteria.add(Restrictions.eq("code", entityCode));
//		if (null != entityId) {
//			criteria.add(Restrictions.ne("id", entityId));
//		}
//		List<?> queryResult = criteria.list();
//		logger.debug("Query result : " + queryResult);
//		return CollectionUtils.isEmpty(queryResult) ? true : false;
//	}
//
//	@Override
//	public void flush() {
//		getCurrentSession().flush();
//	}
//
//}
