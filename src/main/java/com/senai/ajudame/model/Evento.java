package com.senai.ajudame.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String titulo;

	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne(optional = true)
	@JoinColumn(name = "pessoafisica_id")
	@JsonIgnore
	private PessoaFisica fisicaeventos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public PessoaFisica getFisicaeventos() {
		return fisicaeventos;
	}

	public void setFisicaeventos(PessoaFisica fisicaeventos) {
		this.fisicaeventos = fisicaeventos;
	}

}
