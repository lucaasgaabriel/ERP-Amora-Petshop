package com.amorapetshop.controller;

/**
 *  As classes Controllers, reduzem a dependências entre o Model e a View.
 *  tornando o projeto mais flexível, expansível e facilita futuras alterações. 
 */

import java.util.List;

import com.amorapetshop.model.Cliente;
import com.amorapetshop.model.dao.ClienteJpaDao;

public class ClienteController {

	ClienteJpaDao pessoaDao;

	public ClienteController() {
		this.pessoaDao = new ClienteJpaDao();
	}
	
	/**
	 * Salva uma cliente
	 * @param cliente
	 */
	public void salvar (Cliente cliente) {
		pessoaDao.salvar(cliente);
	}
	
	/**
	 * Retorna todas as pessoas cadastradas
	 * @return
	 */
	public List<Cliente> buscarTodos(){
		return pessoaDao.buscaTodos();
	}
	
	/**
	 * Retorna todas as pessoas cadastradas com base
	 * nos dados da cliente informada (filtro)
	 * @param cliente
	 * @return
	 */
	public List<Cliente> buscarFiltro(Cliente cliente){
		
		if(cliente.getNome().isEmpty() && cliente.getCpf().isEmpty()
				&& cliente.getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return pessoaDao.buscaFiltro(cliente);
		}
	}
	
	/**
	 * Exclui uma cliente
	 * @param cliente
	 */
	public void excluir(Cliente cliente) {
		pessoaDao.delete(cliente);
	}
	
}
