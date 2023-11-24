package com.amorapetshop.controller;


import java.util.Collections;
import java.util.List;
import com.amorapetshop.model.Agendamento;
import com.amorapetshop.model.dao.AgendamentoJpaDao;

public class AgendamentoController {

    AgendamentoJpaDao agendamentoDao;

    public AgendamentoController() {
        this.agendamentoDao = new AgendamentoJpaDao();
    }

    /**
     * @param agendamento
     */
    public void salvar (Agendamento agendamento) {
        agendamentoDao.salvar(agendamento);
    }
    public void excluir(Agendamento agendamento) {
        agendamentoDao.excluir(agendamento);
    }


    /**
     * @return
     */
    public List<Agendamento> buscarTodos(){
        return agendamentoDao.buscaTodos();
    }

    /**
     * @param agendamento
     * @return
     */
    public List<Agendamento> buscarFiltro(Agendamento agendamento){
        try {
            return agendamentoDao.buscaFiltro(agendamento);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // ou trate a exceção de forma apropriada
        }
    }
}
