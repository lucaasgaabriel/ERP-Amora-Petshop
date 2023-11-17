package com.amorapetshop.controller;

import java.util.List;

import com.amorapetshop.model.Cliente;
import com.amorapetshop.model.dao.ClienteJpaDao;

public class ClienteController {

	ClienteJpaDao clienteDao;

	public ClienteController() {
		this.clienteDao = new ClienteJpaDao();
	}
	
	/**
	 * @param cliente
	 */
	public void salvar (Cliente cliente) {
		clienteDao.salvar(cliente);
	}
	public void excluir(Cliente cliente) {
		clienteDao.delete(cliente);
	}
	
	/**
	 * @return
	 */
	public List<Cliente> buscarTodos(){
		return clienteDao.buscaTodos();
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public List<Cliente> buscarFiltro(Cliente cliente){
		
		if(cliente.getNome().isEmpty() && cliente.getCpf().isEmpty()
				&& cliente.getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return clienteDao.buscaFiltro(cliente);
		}
	}
	
}
