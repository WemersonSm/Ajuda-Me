package com.senai.ajudame.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.senai.ajudame.dto.DoacaoDTO;
import com.senai.ajudame.enums.DoacaoCategoria;
import com.senai.ajudame.enums.PessoaCategoria;
import com.senai.ajudame.model.Doacao;
import com.senai.ajudame.model.PessoaFisica;
import com.senai.ajudame.repository.DoacaoRepository;
import com.senai.ajudame.repository.PessoaFisicaRepository;
import com.senai.ajudame.service.DoacaoService;

@SpringBootTest
@AutoConfigureMockMvc
class DoacaoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// Cria um obj fake de DoacaoRepository
	@MockBean
	DoacaoRepository doacaoRepository;

	@Test
	void salvar_doacao() throws Exception {
		// Instância do obj PessoaFisica
		var doacao = new Doacao();
		doacao.setData(null);
		doacao.setDescricao("Arroz teste");
		doacao.setTelefone("62996582486");
		doacao.setDoacaocategoria(DoacaoCategoria.ALIMENTAR);
		// Simula um mock
		Mockito.when(doacaoRepository.save(Mockito.any())).thenReturn(doacao);
	}

	@Test
	void busca_doacao() throws Exception {

		var doacao = new Doacao();
		doacao.setData(null);
		doacao.setDescricao("tijolo");
		doacao.setTelefone("62995210008");
		doacao.setDoacaocategoria(DoacaoCategoria.HABITACIONAL);
		Mockito.when(doacaoRepository.findAll()).thenReturn(List.of(doacao));
	}
}