package com.senai.ajudame.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.senai.ajudame.enums.DoacaoCategoria;


@Entity
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private DoacaoCategoria doacaocategoria;

	@ManyToOne(optional = false)
	private PessoaJuridica pessoa_juridica;
	
	@ManyToOne(optional = false)
	private PessoaFisica pessoaFisica;

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
}
