package com.senai.ajudame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.ajudame.entity.PessoaFisica;

@Repository
public interface PessoaFisicaDao extends JpaRepository<PessoaFisica, Integer> {

}
