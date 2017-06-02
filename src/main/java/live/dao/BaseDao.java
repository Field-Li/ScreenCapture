package live.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	
	void setEntityClass(Class<T> entityClass);
	
	Serializable insert(T t);  
    void delete(T t);  
    void update(T t);  
    T queryById(long id);  
    List<T> queryList(String hql, Object[] params);
    
    public List<T> queryListByPage(String hql,int pageSize,int page);
    public T queryUniqueObj(String hql, Object[] params);
    public int updateByHql(String hql);
    
    public List<Object[]> queryBySql( String sql, Object[] params, List<List<Serializable>> aliasEntityList);
}