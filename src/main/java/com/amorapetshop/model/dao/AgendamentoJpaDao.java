package com.amorapetshop.model.dao;

import java.util.List;

import com.amorapetshop.model.Agendamento;

public class AgendamentoJpaDao extends EntityJpaDao<Long, Agendamento> {
    public void salvar(Agendamento agendamento) {
        try {
            begin();
            if(agendamento.getId()==0l) {
                insert(agendamento);
            }else {
                update(agendamento);
            }
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void excluir(Agendamento agendamento) {
        try {
            begin();
            delete(agendamento);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    public List buscaFiltro(Agendamento a) {
        return entityManager.createQuery("FROM Agendamento a where a.responsavel like "
                        + " CONCAT('%',:responsavel,'%') or a.tipoAgendamento like :tipoAgendamento order by a.tipoAgendamento ")
                .setParameter("responsavel", a.getResponsavel())
                .setParameter("tipoAgendamento", a.getTipoAgendamento())
                .getResultList();
    }

    public List buscaTodos() {
        return entityManager.createQuery("FROM Agendamento a order by a.tipoAgendamento ")
                .getResultList();
    }


}
