package com.senai.ajudame.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.senai.ajudame.enums.PessoaCategoria;
import com.senai.ajudame.model.PessoaFisica;
import com.senai.ajudame.repository.PessoaFisicaRepository;
import com.sun.xml.bind.api.impl.NameConverter.Standard;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaFisicaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// Cria um obj fake de PessoaFisicaRepository
	@MockBean
	PessoaFisicaRepository pessoaFisicaRepository;

	@Test
	void busca_a_Pessoa() throws Exception {
		// Instância do obj PessoaFisica
		var pessoaFisica = new PessoaFisica();

		pessoaFisica.setId(null);
		pessoaFisica.setNome("Carlos Henrique");
		pessoaFisica.setCpf("987.546.541-15");
		pessoaFisica.setEmail("carlos@mail.com");
		pessoaFisica.setTelefone("62995254569");
		pessoaFisica.setPessoacategoria(PessoaCategoria.DOADOR);

		// Simula um mock
		Mockito.when(pessoaFisicaRepository.findAll()).thenReturn(List.of(pessoaFisica));
	}

	@Test
	void salvarPessoaFisica() throws Exception {

		var pessoaFisica = new PessoaFisica();
		pessoaFisica.setId(null);
		pessoaFisica.setNome("Rodrigo Santos");
		pessoaFisica.setCpf("123.582.987-40");
		pessoaFisica.setEmail("RS@mail.com");
		pessoaFisica.setTelefone("62996250148");
		pessoaFisica.setPessoacategoria(PessoaCategoria.RECEBER_AJUDA);
		Mockito.when(pessoaFisicaRepository.save(Mockito.any())).thenReturn(pessoaFisica);
	}
}
