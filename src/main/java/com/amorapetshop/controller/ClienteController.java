package com.amorapetshop.controller;

import java.util.Collections;
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
		clienteDao.excluir(cliente);
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
		try {
			return clienteDao.buscaFiltro(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // ou trate a exceção de forma apropriada
		}
	}
	
}
