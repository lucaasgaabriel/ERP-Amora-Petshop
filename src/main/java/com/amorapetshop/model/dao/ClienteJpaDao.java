package com.amorapetshop.model.dao;

import java.util.List;

import com.amorapetshop.model.Cliente;

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
		try {
			begin();
			delete(cliente);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
	}

	public List buscaFiltro(Cliente c) {
		return entityManager.createQuery("FROM Cliente c where c.nome like "
						+ " CONCAT('%',:nome,'%') or c.cpf like :cpf or c.telefone like :telefone order by c.nome ")
				.setParameter("nome", c.getNome())
				.setParameter("cpf", c.getCpf())
				.setParameter("telefone", c.getTelefone())
				.getResultList();
	}
	
	public List buscaTodos() {
        return entityManager.createQuery("FROM Cliente c order by c.nome ")
        		     		.getResultList();
    }
	
}
