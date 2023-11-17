package com.amorapetshop.controller;

/**
 *  As classes Controllers, reduzem a dependências entre o Model e a View.
 *  tornando o projeto mais flexível, expansível e facilita futuras alterações.
 */

import java.util.List;

import com.amorapetshop.model.Produto;
import com.amorapetshop.model.dao.ProdutoJpaDao;

public class ProdutoController {

    ProdutoJpaDao produtoDao;

    public ProdutoController() {
        this.produtoDao = new ProdutoJpaDao();
    }

    /**
     * Salva uma cliente
     * @param produto
     */
    public void salvar (Produto produto) {
        produtoDao.salvar(produto);
    }

    /**
     * Retorna todas as pessoas cadastradas
     * @return
     */
    public List<Produto> buscarTodos(){
        return produtoDao.buscaTodos();
    }

    /**
     * Retorna todas as pessoas cadastradas com base
     * nos dados da cliente informada (filtro)
     * @param produto
     * @return
     */
    public List<Produto> buscarFiltro(Produto produto){

        if(produto.getNome().isEmpty()
                ) {
            return buscarTodos();
        }else {
            return produtoDao.buscaFiltro(produto);
        }
    }

    /**
     * Exclui uma cliente
     * @param produto
     */
    public void excluir(Produto produto) {
        produtoDao.delete(produto);
    }

}
