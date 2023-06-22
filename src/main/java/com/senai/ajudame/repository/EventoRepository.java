package com.senai.ajudame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.ajudame.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>{

}
