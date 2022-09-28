package com.senai.ajudame.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.senai.ajudame.enums.TipoPessoas;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(value = "Nome Pessoa")
	private String nome;

	private String telefone;

	private String email;

	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoPessoas tipopessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoPessoas getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(TipoPessoas tipopessoa) {
		this.tipopessoa = tipopessoa;
	}
	

}
