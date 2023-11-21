package com.amorapetshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Agendamento {
    @Id @GeneratedValue
    private Long id= 0L;
    private String tipoAgendamento;
    private Date dataAgendamento;
    private String horaAgendamento;
    private String responsavel;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoAgendamento(){ return tipoAgendamento;}
    public void setTipoAgendamento(String tipoAgendamento){ this.tipoAgendamento = tipoAgendamento;}
    public Date getDataAgendamento() {
        return dataAgendamento;
    }
    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
    public String getHoraAgendamento() {
        return horaAgendamento;
    }
    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

}
