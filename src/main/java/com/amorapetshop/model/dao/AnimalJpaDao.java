package com.amorapetshop.model.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.amorapetshop.model.Animal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



public class AnimalJpaDao extends EntityJpaDao<Long, Animal> {
    public void salvar(Animal animal) {
        try {
            begin();
            if (animal.getId() == 0L) {
                insert(animal);
            } else {
                update(animal);
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void excluir(Animal animal) {
        EntityTransaction transaction = null;

        try {
            // Inicia a transação
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Recarrega a entidade para garantir que ela seja gerenciada pela sessão
            animal = entityManager.find(Animal.class, animal.getId());

            // Realiza a operação de exclusão
            entityManager.remove(animal);

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



    public List<Animal> buscaFiltro(Animal a) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);

        List<Predicate> predicates = new ArrayList<>();

        if (a.getNome() != null && !a.getNome().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + a.getNome() + "%"));
        }
        if (a.getEspecie() != null && !a.getEspecie().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("especie"), "%" + a.getEspecie() + "%"));
        }
        if (a.getRaca() != null && !a.getRaca().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("raca"), "%" + a.getRaca() + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

        TypedQuery<Animal> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List buscaTodos() {
        return entityManager.createQuery("FROM Animal a order by a.nome ")
                .getResultList();
    }

}
