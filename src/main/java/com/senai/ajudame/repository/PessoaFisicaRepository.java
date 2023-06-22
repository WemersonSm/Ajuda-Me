package com.senai.ajudame.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senai.ajudame.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

	 @Query(value ="SELECT * FROM tb_pessoafisica WHERE tb_pessoafisica.categoriapessoa  = 'DOADOR'", nativeQuery = true)
	 List<PessoaFisica> buscarPorDoador();
	 
	 @Query(value ="SELECT * FROM tb_pessoafisica WHERE tb_pessoafisica.categoriapessoa  = 'RECEBER_AJUDA'", nativeQuery = true)
	 List<PessoaFisica> buscarPorReceberAjuda();
	 
	 @Query(value ="SELECT * FROM tb_pessoafisica WHERE tb_pessoafisica.categoriapessoa  = 'ADMIN'", nativeQuery = true)
	 List<PessoaFisica> buscarPorAdmin();
	 
	 PessoaFisica findByEmail(String email);

}
