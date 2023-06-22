package com.senai.ajudame.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senai.ajudame.enums.DoacaoCategoria;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DoacaoDTO {

	private Integer id;

	@NotBlank(message = "{DoacaoDTO.descricao.NotBlank}")
	private String descricao;

	@NotNull(message = "{DoacaoDTO.data.NotNull}")
	private Date data;

	@NotNull(message = "{DoacaoDTO.doacaocategoria.NotNull}")
	private DoacaoCategoria doacaocategoria;

	private String telefone;

	private PessoaFisicaDTO fisica;

	private PessoaJuridicaDTO juridica;
	
	private String bairro;
	
	private String cep;


	private int pf_id;

	private int pj_id;

	public DoacaoDTO() {
	}

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

	public PessoaFisicaDTO getFisica() {
		return fisica;
	}

	public void setFisica(PessoaFisicaDTO fisica) {
		this.fisica = fisica;
	}

	public PessoaJuridicaDTO getJuridica() {
		return juridica;
	}

	public void setJuridica(PessoaJuridicaDTO juridica) {
		this.juridica = juridica;
	}

	public int getPf_id() {
		return pf_id;
	}

	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
	}

	public int getPj_id() {
		return pj_id;
	}

	public void setPj_id(int pj_id) {
		this.pj_id = pj_id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

}
