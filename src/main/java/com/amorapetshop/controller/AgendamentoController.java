package com.amorapetshop.controller;

/**
 *  As classes Controllers, reduzem a dependências entre o Model e a View.
 *  tornando o projeto mais flexível, expansível e facilita futuras alterações.
 */

import java.util.List;
import com.amorapetshop.model.Agendamento;
import com.amorapetshop.model.dao.AgendamentoJpaDao;

public class AgendamentoController {

    AgendamentoJpaDao agendamentoDao;

    public AgendamentoController() {
        this.agendamentoDao = new AgendamentoJpaDao();
    }

    /**
     * Salva uma pessoa
     * @param agendamento
     */
    public void salvar (Agendamento agendamento) {
        agendamentoDao.salvar(agendamento);
    }

    /**
     * Retorna todas as pessoas cadastradas
     * @return
     */
    public List<Agendamento> buscarTodos(){
        return agendamentoDao.buscaTodos();
    }

    /**
     * Retorna todas as pessoas cadastradas com base
     * nos dados da pessoa informada (filtro)
     * @param agendamento
     * @return
     */
    public List<Agendamento> buscarFiltro(Agendamento agendamento){

        if(agendamento.getResponsavel().isEmpty() && agendamento.getTipoAgendamento().isEmpty()
                ) {
            return buscarTodos();
        }else {
            return agendamentoDao.buscaFiltro(agendamento);
        }
    }

    /**
     * Exclui uma pessoa
     * @param agendamento
     */
    public void excluir(Agendamento agendamento) {
        agendamentoDao.delete(agendamento);
    }

}