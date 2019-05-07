package com.reliant.sm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.util.HibernateSessionFactoryUtil;
/**
 * @author bbachin1
 * 
 */
@Component
@SuppressWarnings("rawtypes")
public class BaseHibernateDAO {
	
	@Autowired
	private SessionFactory smartMainSessionFactory;
	
	
	public List findByNamedQuery(String queryName, String[] paramNames,
			Object[] paramValues) {
		return findByNamedQuery(queryName, paramNames, paramValues, 0, -1, true);
	}

	public List findByNamedQuery(String queryName, String[] paramNames,
			Object[] paramValues, int start, int maxResults) {
		return findByNamedQuery(queryName, paramNames, paramValues, start,
				maxResults, true);
	}
	
	
	public List findByNamedQuery(String queryName, String[] paramNames,
			Object[] paramValues, int start, int maxResults, boolean cached) {
		
		List result = null;
		Transaction tx = null;
		try{
			Session session = HibernateSessionFactoryUtil.getSession(smartMainSessionFactory);
	        tx = session.beginTransaction();
	        Query namedQuery = (Query) session.getNamedQuery(queryName);
			for (int i = 0; i < paramNames.length; i++) {
				String paramName = paramNames[i];
				Object paramValue = paramValues[i];
				namedQuery.setParameter(paramName, paramValue);
			}
			if (maxResults >= 0) {
				namedQuery.setMaxResults(maxResults);
			}
			if (start >= 0) {
				namedQuery.setFirstResult(start);
			}
			result = namedQuery.list();
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			HibernateSessionFactoryUtil.closeSession(smartMainSessionFactory.getCurrentSession());
		}
		return result;
	}
	
	
	public Number findByNamedQueryInt(String queryName, String[] paramNames, Object[] paramValues) {
        return findByNamedQueryInt(queryName, paramNames, paramValues, 0, -1, true);
    }
	
	public String findByNamedQueryString(String queryName, String[] paramNames, Object[] paramValues) {
        return findByNamedQueryString(queryName, paramNames, paramValues, 0, -1, true);
    }

    public Number findByNamedQueryInt(String queryName, String[] paramNames, Object[] paramValues, int start, int maxResults) {
        return findByNamedQueryInt(queryName, paramNames, paramValues, start, maxResults, true);
    }
    
    public Number updateNamedQuery(String queryName, String[] paramNames, Object[] paramValues) {
        return updateNamedQuery(queryName, paramNames, paramValues, true);
    }

    public Number findByNamedQueryInt(String queryName, String[] paramNames, Object[] paramValues, int start, int maxResults, boolean cached) {
    	
    	Transaction tx = null;
    	Number result = 0;
	    try{
	    	Session session = HibernateSessionFactoryUtil.getSession(smartMainSessionFactory);
	        tx = session.beginTransaction();
	        Query namedQuery = (Query) session.getNamedQuery(queryName);
	        for (int i = 0; i < paramNames.length; i++) {
	            String paramName = paramNames[i];
	            Object paramValue = paramValues[i];
	            namedQuery.setParameter(paramName, paramValue);
	        }
	        //namedQuery.setHint("org.hibernate.cacheable", cached);
	        if (maxResults >= 0) {
	            namedQuery.setMaxResults(maxResults);
	        }
	        if (start >= 0) {
	            namedQuery.setFirstResult(start);
	        }
	        result = (Number) namedQuery.list().get(0);
	        tx.commit();
	    }catch(Exception ex){
			tx.rollback();
		}finally{
			HibernateSessionFactoryUtil.closeSession(smartMainSessionFactory.getCurrentSession());
		}
        return result;
    }
    
    
    public String findByNamedQueryString(String queryName, String[] paramNames, Object[] paramValues, int start, int maxResults, boolean cached) {
	    
    	Transaction tx = null;
    	String result = null;
    	try{
    		Session session = HibernateSessionFactoryUtil.getSession(smartMainSessionFactory);
	        tx = session.beginTransaction();
	        Query namedQuery = (Query) session.getNamedQuery(queryName);
	        for (int i = 0; i < paramNames.length; i++) {
	            String paramName = paramNames[i];
	            Object paramValue = paramValues[i];
	            namedQuery.setParameter(paramName, paramValue);
	        }
	        if (maxResults >= 0) {
	            namedQuery.setMaxResults(maxResults);
	        }
	        if (start >= 0) {
	            namedQuery.setFirstResult(start);
	        }
	        result = (String) namedQuery.list().get(0);
	        tx.commit();
	    }catch(Exception ex){
			tx.rollback();
		}finally{
			HibernateSessionFactoryUtil.closeSession(smartMainSessionFactory.getCurrentSession());
		}
	    return result;
    }
    
    
    public int updateNamedQuery(String queryName, String[] paramNames, Object[] paramValues, boolean cached) {
    	int result = 0;
    	Transaction tx = null;
    	try{
	    	Session session = HibernateSessionFactoryUtil.getSession(smartMainSessionFactory);
	        tx = session.beginTransaction();
	        Query namedQuery = (Query) session.getNamedQuery(queryName);
	        for (int i = 0; i < paramNames.length; i++) {
	            String paramName = paramNames[i];
	            Object paramValue = paramValues[i];
	            namedQuery.setParameter(paramName, paramValue);
	        }
	        result = namedQuery.executeUpdate();
	        tx.commit();
	    }catch(Exception ex){
    		tx.rollback();
    	}finally{
    		HibernateSessionFactoryUtil.closeSession(smartMainSessionFactory.getCurrentSession());
    	}
        return result;
    }

}
