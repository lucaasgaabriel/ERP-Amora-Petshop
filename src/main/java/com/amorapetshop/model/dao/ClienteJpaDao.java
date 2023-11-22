package com.amorapetshop.model.dao;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.amorapetshop.model.Cliente;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ClienteJpaDao extends EntityJpaDao<Long, Cliente> {
	public void salvar(Cliente cliente) {
		
		try {
			begin();
			if(cliente.getId()== 0L) {
				insert(cliente);
			}else {
				update(cliente);
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}		
	}
	public void excluir(Cliente cliente) {
		EntityTransaction transaction = null;
		try {
			// Inicia a transação
			transaction = entityManager.getTransaction();
			transaction.begin();

			cliente = entityManager.find(Cliente.class, cliente.getId());

			// Verifica se o cliente está gerenciado antes de excluí-lo
			if (!entityManager.contains(cliente)) {
				// Se não está gerenciado, faz um merge para torná-lo gerenciado
				cliente = entityManager.merge(cliente);
			}

			// Realiza a operação de exclusão
			entityManager.remove(cliente);

			// Comita a transação se tudo ocorreu sem problemas
			transaction.commit();
		} catch (Exception e) {
			// Em caso de exceção, realiza o rollback da transação
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}



	public List<Cliente> buscaFiltro(Cliente c) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		List<Predicate> predicates = new ArrayList<>();

		if (c.getNome() != null && !c.getNome().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("nome"), "%" + c.getNome() + "%"));
		}
		if (c.getCpf() != null && !c.getCpf().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("cpf"), "%" + c.getCpf() + "%"));
		}
		if (c.getTelefone() != null && !c.getTelefone().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("telefone"), "%" + c.getTelefone() + "%"));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

		TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public List buscaTodos() {
        return entityManager.createQuery("FROM Cliente c order by c.nome ")
        		     		.getResultList();
    }
	
}
