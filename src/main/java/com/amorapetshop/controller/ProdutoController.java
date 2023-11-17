package com.amorapetshop.controller;

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
        produtoDao.delete(produto);
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

        if(produto.getNome().isEmpty()
                ) {
            return buscarTodos();
        }else {
            return produtoDao.buscaFiltro(produto);
        }
    }

}
