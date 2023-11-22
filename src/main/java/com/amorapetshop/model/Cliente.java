package com.amorapetshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Cliente {
	@Id @GeneratedValue
	private Long id= 0L;
	private String nome;
	private String cpf;
	private String telefone;
	private String sexo;
	private String donoAnimal;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDonoAnimal() {return donoAnimal;}

	public void setDonoAnimal(String donoAnimal) {this.donoAnimal = donoAnimal;}
}
