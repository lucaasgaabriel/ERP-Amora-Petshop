package com.amorapetshop.model.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.amorapetshop.model.Agendamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class AgendamentoJpaDao extends EntityJpaDao<Long, Agendamento> {
    public void salvar(Agendamento agendamento) {
        try {
            begin();
            if(agendamento.getId()== 0L) {
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
        EntityTransaction transaction = null;
        try {
            // Inicia a transação
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Recarrega a entidade para garantir que ela seja gerenciada pela sessão
            agendamento = entityManager.find(Agendamento.class, agendamento.getId());

            // Realiza a operação de exclusão
            entityManager.remove(agendamento);

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

    public List<Agendamento> buscaFiltro(Agendamento a) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agendamento> criteriaQuery = criteriaBuilder.createQuery(Agendamento.class);
        Root<Agendamento> root = criteriaQuery.from(Agendamento.class);

        List<Predicate> predicates = new ArrayList<>();

        if (a.getResponsavel() != null && !a.getResponsavel().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("responsavel"), "%" + a.getResponsavel() + "%"));
        }
        //tipo de agendamento vasio = null


        // Verifica se o tipoAgendamento não é nulo antes de adicionar a condição
        if (a.getTipoAgendamento() != null) {
            predicates.add(criteriaBuilder.equal(root.get("tipoAgendamento"), a.getTipoAgendamento()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("tipoAgendamento")));

        TypedQuery<Agendamento> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List buscaTodos() {
        return entityManager.createQuery("FROM Agendamento a order by a.tipoAgendamento ")
                .getResultList();
    }


}
