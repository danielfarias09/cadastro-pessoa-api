package br.com.danielfarias.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.danielfarias.model.BaseEntity;

@NoRepositoryBean
public interface BaseDao<E extends BaseEntity<?>> extends CrudRepository<E, Serializable> {
	
	Optional<E> findById(Serializable id);

    E findByParameters(Map<String, String> parameters);

    List<E> findAllByParameters(Map<String, String> parameters);

}
