package com.senai.ajudame.controller;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ajudame.dto.EventoDTO;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.Evento;
import com.senai.ajudame.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EventoService eventoservice;

	@GetMapping
	public ResponseEntity<?> getEventos() {
		return ResponseEntity.ok().body(eventoservice.getListEvento().stream()
				.map(evento -> modelMapper.map(evento, EventoDTO.class)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEventoById(@PathVariable Integer id) {
		try {
			Evento evento = eventoservice.getEventoById(id);

			// convert entity to DTO
			EventoDTO eventoDto = modelMapper.map(evento, EventoDTO.class);
			return new ResponseEntity<>(eventoDto, HttpStatus.OK);

		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	@PostMapping
	public ResponseEntity<?> saveEvento(@RequestBody EventoDTO eventoDto) {

		try {
			// convert DTO to entity
			Evento evento = modelMapper.map(eventoDto, Evento.class);
			evento = eventoservice.salvarEvento(evento);
			// convert entity to DTO
			EventoDTO eventoResponse = modelMapper.map(evento, EventoDTO.class);
			return new ResponseEntity<>(eventoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			// return new ResponseEntity<>(doacaoDto + " e", HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException(eventoDto);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEvento(@PathVariable Integer id, @RequestBody EventoDTO eventoDto) {
		try {
			// convert DTO to entity
			Evento evento = modelMapper.map(eventoDto, Evento.class);
			evento = eventoservice.updateEvento(id, evento);
			// convert entity to DTO
			EventoDTO eventoResponse = modelMapper.map(evento, EventoDTO.class);
			return new ResponseEntity<>(eventoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			// return new ResponseEntity<>(doacaoDto + " e", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(new ResourceNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEvento(@PathVariable Integer id) {
		Evento evento = eventoservice.getEventoById(id);
		eventoservice.deleteEvento(evento);
	}

}
