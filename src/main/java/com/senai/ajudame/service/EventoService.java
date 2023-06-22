package com.senai.ajudame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.Evento;
import com.senai.ajudame.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private PessoaFisicaService pessoafisicaservice;

	public EventoService(EventoRepository eventoRepository) {
		super();
		this.eventoRepository = eventoRepository;
	}

	public List<Evento> getListEvento() {
		return eventoRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Evento salvarEvento(Evento evento) {
		return eventoRepository.save(evento);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Evento updateEvento(int id, Evento eventoRequest) {
		try {
			Evento evento = eventoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(eventoRequest));
			evento.setTitulo(eventoRequest.getTitulo());
			evento.setDescricao(eventoRequest.getDescricao());
			evento.setData(eventoRequest.getData());
			if (eventoRequest.getFisicaeventos() != null && eventoRequest.getFisicaeventos().getId() != null) {
				evento.setFisicaeventos(pessoafisicaservice.getPessoaFisById(eventoRequest.getFisicaeventos().getId()));
			}
			return eventoRepository.save(evento);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(eventoRequest);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteEvento(Evento evento) {
		try {
			eventoRepository.deleteById(evento.getId());
			;
		} catch (Exception e) {
			throw new RuntimeException(
					String.format("Erro ao excluir o Evento %d: %s", evento.getId(), e.getMessage()));
		}
	}

	public Evento getEventoById(int id) {
		Optional<Evento> obj = eventoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}
}
