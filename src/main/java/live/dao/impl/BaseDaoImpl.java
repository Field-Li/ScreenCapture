package live.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import live.dao.BaseDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private static Logger log = LogManager.getLogger(BaseDaoImpl.class);
	
    protected Class<T> entityClass;  
    
    @Autowired  
    private SessionFactory sessionFactory;  
	
	public BaseDaoImpl(){  
		
    }
  
    public Class<T> getEntityClass() {
		return entityClass;
	}

    @Override
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

    @Override  
    public Serializable insert(T t) {  
        return sessionFactory.getCurrentSession().save(t);  
    }  
  
    @Override  
    public void delete(T t) {  
        sessionFactory.getCurrentSession().delete(t);  
    }  
  
    @Override  
    public void update(T t) {  
        sessionFactory.getCurrentSession().update(t);  
    }  
    
     public int updateByHql(String hql){
          return sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
     }
    
    @SuppressWarnings("unchecked")
	@Override
    public T queryById(long id) {  
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);  
    }  
    
    @SuppressWarnings("unchecked")
	@Override
    public List<T> queryList(String hql, Object[] params) {  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        setQueryParams(query, params);  
        return query.list();  
    }  
    
    public T queryUniqueObj(String hql, Object[] params) {  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        setQueryParams(query, params);  
        return (T) query.uniqueResult();  
    }
    
    public List<T> queryListByPage(String hql,int pageSize,int page){
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);  
    	query.setFirstResult((page-1)*pageSize); 
    	query.setMaxResults(pageSize); 
    	return query.list();
    }
    
    /**
     * 原生SQL查询 
     */
    public List<Object[]> queryBySql( String sql, Object[] params, List<List<Serializable>> aliasEntityList) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		setQueryParams(query, params);
		setAliasEntity(query, aliasEntityList);
		return query.list();
	}
  
    private void setQueryParams(Query query, Object[] params) {  
        if (null == params) {  
            return;  
        }  
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }  
    }  
    
    private void setAliasEntity(SQLQuery query, List<List<Serializable>> aliasEntityList) {  
        if (null == aliasEntityList) {  
            return;  
        }  
        for ( List<Serializable> aliasEntity : aliasEntityList ){
        	query.addEntity( (String)aliasEntity.get(0), (Class)aliasEntity.get(1) );
        }
    }  

}
