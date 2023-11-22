package com.amorapetshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Produto {
    @Id @GeneratedValue
    private Long id= 0L;
    private String nome;
    private Date dataEntrada;
    private Date dataSaida;
    private Double valor;
    private Integer quantidade;
    private String codigoEAN;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() { return nome;}
    public void setNome(String nome){ this.nome = nome;}

    public Date getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public Date getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Integer getQuantidade(){ return quantidade;}

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodigoEAN() {return codigoEAN;}

    public void setCodigoEAN(String codigoEAN) {this.codigoEAN = codigoEAN;}
}
