package com.senai.ajudame.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.senai.ajudame.model.PessoaFisica;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EventoDTO {

	private int id;

	private String titulo;

	private String descricao;

	private Date data;

	private PessoaFisica fisicaeventos;

	public PessoaFisica getFisicaeventos() {
		return fisicaeventos;
	}

	public void setFisicaeventos(PessoaFisica fisicaeventos) {
		this.fisicaeventos = fisicaeventos;
	}

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
}
