package com.amorapetshop.model.dao;

/**
 * Classe especialista para persistência de Cliente
 */

import java.util.List;
import com.amorapetshop.model.Animal;

public class AnimalJpaDao extends EntityJpaDao<Long, Animal> {
    public void salvar(Animal animal) {

        try {
            begin();
            if(animal.getId()==0l) {
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

    public List<Animal> buscaFiltro(Animal a) {
        return entityManager.createQuery("FROM Animal a where a.nome like "
                        + " CONCAT('%',:nome,'%') or a.especie like :especie or a.raca like :raca order by a.nome ")
                .setParameter("nome", a.getNome())
                .setParameter("especie", a.getEspecie())
                .setParameter("raca", a.getRaca())
                .getResultList();
    }

    public List<Animal> buscaTodos() {
        return entityManager.createQuery("FROM Animal a order by a.nome ")
                .getResultList();
    }


}
