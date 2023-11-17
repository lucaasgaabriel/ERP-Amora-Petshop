package com.amorapetshop.controller;

/**
 *  As classes Controllers, reduzem a dependências entre o Model e a View.
 *  tornando o projeto mais flexível, expansível e facilita futuras alterações.
 */

import java.util.List;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.dao.AnimalJpaDao;

public class AnimalController {

    AnimalJpaDao animalDao;

    public AnimalController() {
        this.animalDao = new AnimalJpaDao();
    }

    /**
     * Salva uma pessoa
     * @param animal
     */
    public void salvar (Animal animal) {
        animalDao.salvar(animal);
    }

    /**
     * Retorna todas as pessoas cadastradas
     * @return
     */
    public List<Animal> buscarTodos(){
        return animalDao.buscaTodos();
    }

    /**
     * Retorna todas as pessoas cadastradas com base
     * nos dados da pessoa informada (filtro)
     * @param animal
     * @return
     */
    public List<Animal> buscarFiltro(Animal animal){

        if(animal.getNome().isEmpty() && animal.getNome().isEmpty()
                && animal.getEspecie().isEmpty()) {
            return buscarTodos();
        }else {
            return animalDao.buscaFiltro(animal);
        }
    }

    /**
     * Exclui uma pessoa
     * @param animal
     */
    public void excluir(Animal animal) {
        animalDao.delete(animal);
    }

}
