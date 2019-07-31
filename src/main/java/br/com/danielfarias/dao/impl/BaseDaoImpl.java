package br.com.danielfarias.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.internal.SessionImpl;
import org.springframework.transaction.annotation.Transactional;

import br.com.danielfarias.dao.BaseDao;
import br.com.danielfarias.model.BaseEntity;

@Transactional
public abstract class BaseDaoImpl<E extends BaseEntity<?>> implements BaseDao<E> {
	
	@PersistenceContext
    private EntityManager entityManager;

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
    }

    protected CallableStatement call(String query) throws SQLException {
        return getSession().connection().prepareCall(query);
    }

    @Override
    public long count() {
        return entityManager.createQuery(String.format("SELECT COUNT(*) FROM %s", clazz.getSimpleName()), Long.class).getSingleResult();
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void delete(Iterable<? extends E> entities) {
        for (E entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void delete(Serializable id) {
        delete(findOne(id));
    }

    @Override
    public final void deleteAll() {
        // Not Possible
    }

    @Override
    public boolean exists(Serializable id) {
        return findOne(id) != null;
    }

    @Override
    public ArrayList<E> findAll() {
        return (ArrayList<E>) entityManager.createQuery(String.format("FROM %s", clazz.getSimpleName()), clazz).getResultList();
    }

    @Override
    public ArrayList<E> findAll(Iterable<Serializable> ids) {
        ArrayList<E> ArrayLista = new ArrayList<>();
        for (Serializable id : ids) {
            ArrayLista.add(findOne(id));
        }
        return ArrayLista;
    }

    @Override
    public List<E> findAllByParameters(Map<String, String> parameters) {
        try {
            return getQuery(parameters).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public E findByParameters(Map<String, String> parameters) {
        try {
            return getQuery(parameters).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public E findOne(Serializable id) {
        return entityManager.find(clazz, id);
    }

    protected String getHQL(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(String.format("FROM %s", clazz.getSimpleName()));
            if (parameters.size() > 0) {
                sb.append(" WHERE");
                boolean primeiro = true;
                for (String parameter : parameters.keySet()) {
                    if (primeiro) {
                        primeiro = false;
                    } else {
                        sb.append(" AND");
                    }
                    sb.append(" ").append(parameter).append(" = :").append(parameter.toUpperCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    protected TypedQuery<E> getQuery(Map<String, String> parameters) {
        TypedQuery<E> query = entityManager.createQuery(getHQL(parameters), clazz);
        try {
            for (String parameter : parameters.keySet()) {
                Field field;
                field = clazz.getDeclaredField(parameter);
                field.setAccessible(true);
                switch (field.getType().getCanonicalName()) {
                case "java.lang.Integer":
                    query.setParameter(parameter.toUpperCase(), Integer.parseInt(parameters.get(parameter)));
                    break;
                case "java.lang.String":
                    query.setParameter(parameter.toUpperCase(), parameters.get(parameter));
                    break;
                case "java.lang.Long":
                    query.setParameter(parameter.toUpperCase(), Long.parseLong(parameters.get(parameter)));
                    break;
                case "java.lang.Double":
                    query.setParameter(parameter.toUpperCase(), Double.parseDouble(parameters.get(parameter)));
                    break;
                case "java.lang.Float":
                    query.setParameter(parameter.toUpperCase(), Float.parseFloat(parameters.get(parameter)));
                    break;
                default:
                    query.setParameter(parameter.toUpperCase(), parameters.get(parameter));
                    break;
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }

    protected SessionImpl getSession() {
        return (SessionImpl) entityManager.getDelegate();
    }

    protected TypedQuery<E> getSingleResultQuery(String hql) {
        TypedQuery<E> query = entityManager.createQuery(hql, clazz);
        query.setMaxResults(1);
        return query;
    }

    protected <T> TypedQuery<T> getSingleResultQuery(String hql, Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery(hql, clazz);
        query.setMaxResults(1);
        return query;
    }

    protected Query nativeQuery(String query) {
        return entityManager.createNativeQuery(query);
    }
    
    protected Query queryy(String query) {
        return entityManager.createQuery(query);
    }

    protected Query nativeQuery(String query, Class<?> clazz) throws SQLException {
        return entityManager.createNativeQuery(query, clazz);
    }

    protected TypedQuery<E> query(String query) {
        return entityManager.createQuery(query, clazz);
    }

    protected <T> TypedQuery<T> query(String query, Class<T> clazz) {
        return entityManager.createQuery(query, clazz);
    }

    @Override
    public <S extends E> Iterable<S> save(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public <S extends E> S save(S entity) {
        try {
            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
    
    @Override
	public Optional<E> findById(Serializable id) {
    	return Optional.of(entityManager.find(clazz, id));
	}
}
