//package org.yyf.hibernateexample;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.LockOptions;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Order;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.zznode.se.inmp.domain.basemodel.CodeNameable;
//import com.zznode.se.inmp.tools.commons.Page;
//
///**
// *
// * @author taoping
// *
// */
//public interface EntityManager {
//
//	/**
//	 * load返回entity的代理，只有在访问entity的方法的时候才会真正触发数据库的操作，对于那些只需要维护 entity之间的关联关系的情况下，用load方法可以提高性能。
//	 */
//	<T extends Object> T load(Class<T> clazz, Serializable id);
//
//	/**
//	 * 在符合条件的实例存在的情况下，根据给定的实体类和标识返回持久化状态的实例。如果没有对应的实例，则返回null。
//	 */
//	@Transactional(readOnly = true)
//	<T extends Object> T get(Class<T> clazz, Serializable id);
//
//	/**
//	 * 刷新entity,带事务。
//	 */
//	@Transactional(readOnly = true)
//	<T extends Object> void refresh(T entity);
//
//	/**
//	 * 本方法只能查出未被逻辑删除的对象（即已经默认添加isDeleted=false条件）
//	 *
//	 * @param clazz
//	 * @param currentPageNo
//	 * @param pageSize
//	 * @param orders
//	 * @param criterions
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public <T> Page<T> getPageByCriterion(Class<T> clazz, int currentPageNo, int pageSize, Order[] orders,
//                                          Criterion... criterions);
//
//	@Transactional(readOnly = true)
//	public Long getCountByHql(final String hql, Map<String, Object> queryParameters);
//
//	@Transactional(readOnly = true)
//	public Page getPageByHql(final String hql, Map<String, Object> queryParameters, int currentPageNo, int pageSize);
//
//	/**
//	 * 刷新entity,带事务。
//	 */
//	@Transactional(readOnly = true)
//	<T extends Object> void refresh(T entity, LockOptions lockOptions);
//
//	void flush();
//
//	/**
//	 * 查找所有的entity
//	 */
//	@Transactional(readOnly = true)
//	<T extends Object> List<T> getAll(Class<T> clazz);
//
//	/**
//	 * 查找所有的entity,返回为Hashtable.<br>
//	 * T 支持实现CodeNameable接口的类或枚举<br>
//	 * index表示值对形式： <li>1.name-id</li> <li>2.code-id</li> <li>3.code-name</li> <li>
//	 * 4.id-name</li>
//	 *
//	 * @param <T>
//	 *            extends DomainCategory or DomainGeneral
//	 * @param clazz
//	 * @param index
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	<T extends CodeNameable<?>> Map<String, String> getAllToMap(Class<T> clazz, IndexMapModel index);
//
//	/**
//	 * 保存一个新的entity,并返回唯一标识
//	 */
//	@Transactional
//	Serializable save(Object entity);
//
//	/**
//	 * 批量保存多个新的entity,并返回唯一标识
//	 *
//	 * @param entities
//	 * @return
//	 */
//	@Transactional
//	Serializable[] save(Object[] entities);
//
//	/**
//	 * 批量保存多个新的entity,并返回唯一标识
//	 */
//	@Transactional
//	Serializable[] save(Collection<?> entities);
//
//	/**
//	 * 更新entity
//	 */
//	@Transactional
//	void update(Object entity);
//
//	/**
//	 * 合并实体
//	 */
//	<T extends Object> T merge(Class<T> clazz, T entity);
//
//	/**
//	 * 批量更新entity
//	 */
//	@Transactional
//	void update(Object[] entities);
//
//	/**
//	 * 批量更新entity
//	 */
//	@Transactional
//	void update(Collection<?> entities);
//
//	/**
//	 * 保存entity,如果数据库中没有对应的记录,则直接插入一条新的记录,如果数据库中已经有对应的记录,则直接更新
//	 */
//	@Transactional
//	void saveOrUpdate(Object entity);
//
//	/**
//	 * 删除entity
//	 */
//	@Transactional
//	void delete(Object entity);
//
//	/**
//	 * 批量删除entity
//	 */
//	@Transactional
//	void delete(Object[] entities);
//
//	/**
//	 * 批量删除entity
//	 */
//	@Transactional
//	void delete(Collection<?> entities);
//
//	/**
//	 * 根据id删除该id对应的entity
//	 */
//	@Transactional
//	<T extends Object> void deleteById(Class<T> clazz, Serializable id);
//
//	/**
//	 * 批量根据id删除该id对应的entity
//	 */
//	@Transactional
//	<T extends Object> void deleteById(Class<T> clazz, Serializable[] ids);
//
//	/**
//	 * 批量根据id删除该id对应的entity
//	 */
//	@Transactional
//	<T extends Object> void deleteById(Class<T> clazz, Collection<Serializable> ids);
//
//	/**
//	 * 批量操作，可以是增加，删除，更新混合批量，适合复杂的多个实体关联更新操作
//	 */
//	@Transactional
//	void execute(UnitOfWork unitOfWork);
//
//	/**
//	 * example query
//	 *
//	 * @param example
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	<T extends Object> List<T> findByExample(T example);
//
//	/**
//	 * 验证实体编码是否唯一有效
//	 *
//	 * @param domainType
//	 * @param entityId
//	 * @param entityCode
//	 * @return true 有效，编码唯一; false 无效，编码已存在
//	 */
//	@Transactional(readOnly = true)
//	boolean validateCode(Class<? extends CodeNameable<? extends Serializable>> domainType, Serializable entityId,
//                         String entityCode);
//}
