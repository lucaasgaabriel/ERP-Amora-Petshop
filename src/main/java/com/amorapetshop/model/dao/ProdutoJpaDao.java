package com.amorapetshop.model.dao;

import java.util.List;
import com.amorapetshop.model.Produto;

public class ProdutoJpaDao extends EntityJpaDao<Long, Produto> {
    public void salvar(Produto produto) {

        try {
            begin();
            if(produto.getId()==0l) {
                insert(produto);
            }else {
                update(produto);
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }
    public void excluir(Produto produto) {
        try {
            begin();
            delete(produto);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public List buscaFiltro(Produto p) {
        return entityManager.createQuery("FROM Produto p where p.nome like "
                        + " CONCAT('%',:nome,'%') order by p.nome ")
                .setParameter("nome", p.getNome())
                .getResultList();
    }

    public List buscaTodos() {
        return entityManager.createQuery("FROM Produto p order by p.nome ")
                .getResultList();
    }


}
