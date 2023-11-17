package com.amorapetshop.model.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.amorapetshop.model.Animal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;


public class AnimalJpaDao extends EntityJpaDao<Long, Animal> {
    public void salvar(Animal animal) {

        try {
            begin();
            if(animal.getId()== 0L) {
                insert(animal);
            }else {
                update(animal);
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void excluir(Animal animal) {
        try {
            begin();
            delete(animal);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
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
