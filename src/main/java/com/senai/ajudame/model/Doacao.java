package com.senai.ajudame.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senai.ajudame.enums.DoacaoCategoria;

@Entity
@Table(name = "tb_doacao")
public class Doacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descricao")
	@NotBlank(message = "{Doacao.descricao.NotBlank}")
	private String descricao;

	@Column(name = "data")
	@NotNull(message = "{Doacao.data.NotNull}")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;

	@Column(name = "doacaocategoria")
	@NotNull(message = "{Doacao.doacaocategoria.NotNull}")
	@Enumerated(EnumType.STRING)
	private DoacaoCategoria doacaocategoria;

	@ManyToOne(optional = true)
	@JoinColumn(name = "pessoafisica_id")
	@JsonIgnore
	private PessoaFisica fisica;

	@ManyToOne(optional = true)
	@JoinColumn(name = "pessoajuridica_id")
	@JsonIgnore
	private PessoaJuridica juridica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DoacaoCategoria getDoacaocategoria() {
		return doacaocategoria;
	}

	public void setDoacaocategoria(DoacaoCategoria doacaocategoria) {
		this.doacaocategoria = doacaocategoria;
	}

	public PessoaFisica getFisica() {
		return fisica;
	}

	public void setFisica(PessoaFisica fisica) {
		this.fisica = fisica;
	}

	public PessoaJuridica getJuridica() {
		return juridica;
	}

	public void setJuridica(PessoaJuridica juridica) {
		this.juridica = juridica;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
		Doacao other = (Doacao) obj;
		return Objects.equals(id, other.id);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
