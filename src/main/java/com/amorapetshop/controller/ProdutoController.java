package com.amorapetshop.controller;

import java.util.Collections;
import java.util.List;

import com.amorapetshop.model.Produto;
import com.amorapetshop.model.dao.ProdutoJpaDao;

public class ProdutoController {

    ProdutoJpaDao produtoDao;

    public ProdutoController() {
        this.produtoDao = new ProdutoJpaDao();
    }

    /**
     * @param produto
     */
    public void salvar (Produto produto) {
        produtoDao.salvar(produto);
    }
    public void excluir(Produto produto) {
        produtoDao.excluir(produto);
    }

    /**
     * @return
     */
    public List<Produto> buscarTodos(){
        return produtoDao.buscaTodos();
    }

    /**
     * @param produto
     * @return
     */
    public List buscarFiltro(Produto produto){
        try {
            return produtoDao.buscaFiltro(produto);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // ou trate a exceção de forma apropriada
        }
    }

}
