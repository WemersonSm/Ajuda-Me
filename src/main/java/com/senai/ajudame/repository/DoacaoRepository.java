package com.senai.ajudame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senai.ajudame.enums.DoacaoCategoria;
import com.senai.ajudame.model.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

	@Query(value = "SELECT * FROM tb_doacao WHERE tb_doacao.pessoafisica_id = ?1", nativeQuery = true)
	List<Doacao> getListFisica( Integer id);
	
	@Query(value = "SELECT * FROM tb_doacao WHERE tb_doacao.pessoajuridica_id = ?1", nativeQuery = true)
	List<Doacao> getListJuridica( Integer id);
	
	List<Doacao> findByDoacaocategoria(DoacaoCategoria doacaocategoria);

}
