package br.com.danielfarias.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import br.com.danielfarias.dao.BaseDao;
import br.com.danielfarias.model.BaseEntity;
import br.com.danielfarias.service.BaseService;


@Transactional
public abstract class BaseServiceImpl<E extends BaseEntity<?>> implements BaseService<E> {
	
	 public abstract BaseDao<E> getDao();
	 
	 @Override
	    public <S extends E> S save(S entity) {
	        return getDao().save(entity);
	    }

	    @Override
	    public <S extends E> Iterable<S> save(Iterable<S> entities) {
	        return getDao().save(entities);
	    }

	    @Override
	    public E findOne(Serializable id) {
	        return getDao().findOne(id);
	    }

	    @Override
	    public boolean exists(Serializable id) {
	        return getDao().exists(id);
	    }

	    @Override
	    public Iterable<E> findAll() {
	        return getDao().findAll();
	    }

	    @Override
	    public Iterable<E> findAll(Iterable<Serializable> ids) {
	        return findAll(ids);
	    }

	    @Override
	    public long count() {
	        return getDao().count();
	    }

	    @Override
	    public void delete(Serializable id) {
	        getDao().delete(id);

	    }

	    @Override
	    public void delete(E entity) {
	        getDao().delete(entity);
	    }

	    @Override
	    public void delete(Iterable<? extends E> entities) {
	        getDao().delete(entities);
	    }

	    @Override
	    public void deleteAll() {
	        getDao().deleteAll();
	    }

	    @Override
	    public E findById(Serializable objectId) {
	        return getDao().findOne(objectId);
	    }

	    @Override
	    public E findByParameters(Map<String, String> parameters) {
	        return getDao().findByParameters(parameters);
	    }

	    @Override
	    public List<E> findAllByParameters(Map<String, String> parameters) {
	        return getDao().findAllByParameters(parameters);
	    }
}
