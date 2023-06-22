package com.senai.ajudame.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.senai.ajudame.enums.PessoaCategoria;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	@ApiModelProperty(value = "Nome Pessoa")
	@NotBlank(message = "{Pessoa.nome.NotBlank}")
	private String nome;

	@Column(name = "telefone")
	@NotBlank(message = "{Pessoa.telefone.NotBlank}")
	private String telefone;

	@NotBlank(message = "{Pessoa.email.NotBlank}")
	@Email(message = "{Pessoa.email.Email}")
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	/*
	 * @Column(name = "senha") private String senha;
	 */

	@Column(name = "pessoacategoria")
	@NotNull(message = "{Pessoa.categoriapessoa.NotNull}")
	@Enumerated(EnumType.STRING)
	private PessoaCategoria pessoacategoria;

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

	/*
	 * public String getSenha() { return senha; }
	 * 
	 * public void setSenha(String senha) { this.senha = senha; }
	 */

	public PessoaCategoria getPessoacategoria() {
		return pessoacategoria;
	}

	public void setPessoacategoria(PessoaCategoria pessoacategoria) {
		this.pessoacategoria = pessoacategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

}
