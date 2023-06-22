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
import com.senai.ajudame.enums.PessoaCategoria;
import com.senai.ajudame.model.PessoaFisica;
import com.senai.ajudame.model.PessoaJuridica;
import com.senai.ajudame.repository.PessoaJuridicaRepository;

@SpringBootTest
@AutoConfigureMockMvc

class PessoaJuridicaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// Cria um obj fake de PessoaJuridicaRepository
	@MockBean
	PessoaJuridicaRepository pessoaJuridicaRepository;

	@Test
	void busca_a_Pessoa() throws Exception {
		// Instância do obj PessoaJuridica
		var pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setId(null);
		pessoaJuridica.setNome("Carlos Henrique");
		pessoaJuridica.setCnpj("42.189.503/0001-05");
		pessoaJuridica.setEmail("carlos@mail.com");
		pessoaJuridica.setTelefone("62995254569");
		pessoaJuridica.setPessoacategoria(PessoaCategoria.DOADOR);

		// Simula um mock
		Mockito.when(pessoaJuridicaRepository.findAll()).thenReturn(List.of(pessoaJuridica));
	}

	@Test
	void salvarPessoaJuridica() throws Exception {

		var pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setId(null);
		pessoaJuridica.setNome("Rodrigo Santos");
		pessoaJuridica.setCnpj("76.861.941/0001-81");
		pessoaJuridica.setEmail("RS@mail.com");
		pessoaJuridica.setTelefone("62996250148");
		pessoaJuridica.setPessoacategoria(PessoaCategoria.RECEBER_AJUDA);
		Mockito.when(pessoaJuridicaRepository.save(Mockito.any())).thenReturn(pessoaJuridica);
	}

}