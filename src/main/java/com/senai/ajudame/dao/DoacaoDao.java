package com.senai.ajudame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.ajudame.entity.Doacao;

@Repository
public interface DoacaoDao extends JpaRepository<Doacao, Integer>{

}
