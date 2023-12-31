package com.amorapetshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.amorapetshop.model.Cliente;


@Entity
public class Animal {
    @Id @GeneratedValue
    private Long id = 0L;
    private String nome;
    private String especie;
    private String raca;
    @ManyToOne
    //@JoinColumn(name = "id")
    private Cliente donoid;
    private Long dono;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public Long getDono() {
        return dono;
    }
    public void setDono(Long dono) {
        this.dono = dono;
    }
}
