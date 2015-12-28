package com.pochoF1.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public abstract class GenericDAO<T> {

	@SuppressWarnings("unchecked")
	public Class<T> domainClass = getDomainClass();

	private static Session session;

	/**
	 * Method to return the class of the domain object
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}

	@SuppressWarnings("unchecked")
	public T load(Integer id) {
		T returnValue = null;
		if (id != null) {
			returnValue = (T) getHibernateTemplate().load(domainClass, id);
			session.getTransaction().commit();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public T load(String id) {
		T returnValue = null;
		if (id != null) {
			returnValue = (T) getHibernateTemplate().load(domainClass, id);
			session.getTransaction().commit();
		}
		return returnValue;
	}

	public void update(T t) throws Exception {
		try {
			getHibernateTemplate().update(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void save(T t) throws Exception {
		try {
			getHibernateTemplate().save(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw e;
		}
	}

	public void saveList(List<T> t) throws Exception {
		try {
			for (T obj : t) {
				getHibernateTemplate().save(obj);
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public T get(String id) {
		T returnValue = null;
		if (id != null) {
			returnValue = (T) getHibernateTemplate().get(domainClass, id);
			session.getTransaction().commit();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public T get(Integer id) {
		T returnValue = null;
		if (id != null) {
			returnValue = (T) getHibernateTemplate().get(domainClass, id);
			session.getTransaction().commit();
		}
		return returnValue;
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> getList() {
		List<T> returnList = getHibernateTemplate().createQuery("from " + domainClass.getName() + " x").list();
		session.getTransaction().commit();
		return returnList;
	}

	public void deleteById(Integer id) {
		Object obj = this.load(id);
		session.getTransaction().commit();
		getHibernateTemplate().delete(obj);
	}

	public int count() {
		Integer count = (Integer) getHibernateTemplate().createQuery("select count(*) from " + domainClass.getName() + " x").uniqueResult();
		session.getTransaction().commit();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleObject) {
		Example example = Example.create(exampleObject).excludeZeroes().enableLike().ignoreCase();
		List<T> list = getHibernateTemplate().createCriteria(domainClass).add(example).list();
		session.getTransaction().commit();
		return list;
	}

	protected Session getHibernateTemplate() {

		if (session == null || !session.isConnected() || !session.isOpen()) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		return session;
	}

	protected Session getSession() {
		return session;
	}

	public void commitTransaction() {
		if (session != null) {
			session.getTransaction().commit();
		}
	}

	public void rollbackTransaction() {
		if (session != null) {
			session.getTransaction().rollback();
		}
	}

}
