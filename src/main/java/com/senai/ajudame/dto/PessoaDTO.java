package com.senai.ajudame.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senai.ajudame.enums.PessoaCategoria;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PessoaDTO  {


	private Integer id;

	@NotBlank(message = "{PessoaDTO.nome.NotBlank}")
	private String nome;

	@NotBlank(message = "{PessoaDTO.telefone.NotBlank}")
	private String telefone;

	@NotBlank(message = "{PessoaDTO.email.NotBlank}")
	private String email;

	private String senha;
	
	//private String login;
	
	

//	@NotNull(message = "{PessoaDTO.categoriapessoa.NotNull}")
	private PessoaCategoria pessoacategoria;

	public PessoaDTO() {
		super();
	}

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

	public PessoaCategoria getPessoacategoria() {
		return pessoacategoria;
	}

	public void setPessoacategoria(PessoaCategoria pessoacategoria) {
		this.pessoacategoria = pessoacategoria;
	}
	
}
