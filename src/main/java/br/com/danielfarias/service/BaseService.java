package br.com.danielfarias.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.danielfarias.dao.BaseDao;
import br.com.danielfarias.model.BaseEntity;

@NoRepositoryBean
public interface BaseService<E extends BaseEntity<?>> {
	
	 	E findById(Serializable objectId);

	    E findByParameters(Map<String, String> parameters);

	    List<E> findAllByParameters(Map<String, String> parameters);
	    
	    public abstract BaseDao<E> getDao();
	    
	    public <S extends E> S save(S entity);
	    
	    public <S extends E> Iterable<S> save(Iterable<S> entities);
	    
	    public E findOne(Serializable id);
	    
	    public boolean exists(Serializable id);
	    
	    public Iterable<E> findAll();
	    
	    public Iterable<E> findAll(Iterable<Serializable> ids);
	    
	    public long count();
	    
	    public void delete(Serializable id);
	    
	    public void delete(E entity);

	    public void delete(Iterable<? extends E> entities);
	    
	    public void deleteAll();

}
