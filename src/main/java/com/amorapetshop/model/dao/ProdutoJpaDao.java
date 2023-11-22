package com.amorapetshop.model.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.amorapetshop.model.Produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProdutoJpaDao extends EntityJpaDao<Long, Produto> {
    public void salvar(Produto produto) {

        try {
            begin();
            if(produto.getId()== 0L) {
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
        EntityTransaction transaction = null;
        try {
            // Inicia a transação
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Recarrega a entidade para garantir que ela seja gerenciada pela sessão
            produto = entityManager.find(Produto.class, produto.getId());

            // Realiza a operação de exclusão
            entityManager.remove(produto);

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

    public List<Produto> buscaFiltro(Produto p) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        List<Predicate> predicates = new ArrayList<>();

        if (p.getNome() != null && !p.getNome().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + p.getNome() + "%"));
        }

        // Verifica se o valor não é nulo antes de adicionar a condição
        if (p.getValor() != null) {
            predicates.add(criteriaBuilder.equal(root.get("valor"), p.getValor()));
        }

        // Verifica se a data de entrada não é nula antes de adicionar a condição
        if (p.getDataEntrada() != null) {
            predicates.add(criteriaBuilder.equal(root.get("dataEntrada"), p.getDataEntrada()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

        TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List buscaTodos() {
        return entityManager.createQuery("FROM Produto p order by p.nome ")
                .getResultList();
    }
}
