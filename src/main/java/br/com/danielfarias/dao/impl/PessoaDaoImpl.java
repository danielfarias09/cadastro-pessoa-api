package br.com.danielfarias.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import br.com.danielfarias.dao.PessoaDao;
import br.com.danielfarias.model.Pessoa;
import br.com.danielfarias.to.PessoaTO;

@Repository("pessoaDao")
public class PessoaDaoImpl extends BaseDaoImpl<Pessoa> implements PessoaDao{
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Pessoa> findPessoas(PessoaTO to) {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("FROM Pessoa p WHERE 1=1");
			if(!ObjectUtils.isEmpty(to.getNome())) {
				hql.append("AND lower(p.nome) like :nome");
			}	
			if(!ObjectUtils.isEmpty(to.getCpf())) {
				hql.append("AND p.cpf = :cpf");
			}	
			
			TypedQuery<Pessoa> query = query(hql.toString());
			if(!ObjectUtils.isEmpty(to.getNome())) {
				query.setParameter("nome", "%"+ to.getNome().toLowerCase() +"%");
			}	
			if(!ObjectUtils.isEmpty(to.getCpf())) {
				query.setParameter("cpf", to.getCpf());
			}	
			return query.getResultList();
		}catch(NoResultException e){
			throw new RuntimeException("Não existe pessoas com os paramêtros informados.");
		}catch (Exception e) {
            throw new RuntimeException(e);
        }
		
	}



}
