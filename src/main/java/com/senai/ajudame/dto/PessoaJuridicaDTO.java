package com.senai.ajudame.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PessoaJuridicaDTO extends PessoaDTO {

	@NotBlank(message = "{PessoaJuridicaDTO.cnpj.NotBlank}")
	private String cnpj;

	private String razaosocial;

	@JsonIgnore
	private List<DoacaoDTO> doacoes = new ArrayList<>();

	public List<DoacaoDTO> getDoacoes() {
		return doacoes;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
}
