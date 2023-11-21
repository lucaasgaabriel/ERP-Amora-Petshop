package com.amorapetshop.controller;

import java.util.List;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.dao.AnimalJpaDao;

import java.util.Collections;


public class AnimalController {

    AnimalJpaDao animalDao;

    public AnimalController() {
        this.animalDao = new AnimalJpaDao();
    }

    /**
     * @param animal
     */
    public void salvar (Animal animal) {
        animalDao.salvar(animal);
    }
    public void excluir (Animal animal) { animalDao.excluir(animal);}

    /**
     * @return
     */
    public List<Animal> buscarTodos(){
        return animalDao.buscaTodos();
    }

    /**
     * @param animal
     * @return
     */
    public List<Animal> buscarFiltro(Animal animal){
        try {
            return animalDao.buscaFiltro(animal);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // ou trate a exceção de forma apropriada
        }
    }
}

