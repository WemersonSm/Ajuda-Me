package com.senai.ajudame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.ajudame.entity.PessoaJuridica;

@Repository
public interface PessoaJuridicaDao extends JpaRepository<PessoaJuridica, Integer>{

}
