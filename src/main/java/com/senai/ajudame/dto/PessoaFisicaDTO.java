package com.senai.ajudame.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PessoaFisicaDTO extends PessoaDTO {

	@NotBlank(message = "{PessoaFisicaDTO.cpf.NotBlank}")
	private String cpf;

	@JsonIgnore
	private List<DoacaoDTO> doacoes = new ArrayList<>();
	
	@JsonIgnore
	private List<EventoDTO> eventos = new ArrayList<>();

	public List<DoacaoDTO> getDoacoes() {
		return doacoes;
	}

	public List<EventoDTO> getEventos() {
		return eventos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
