package com.senai.ajudame.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.senai.ajudame.dto.DoacaoDTO;
import com.senai.ajudame.enums.DoacaoCategoria;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.Doacao;
import com.senai.ajudame.service.DoacaoService;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DoacaoService doacaoservice;

	@GetMapping
	public ResponseEntity<?> getDoacao() {
		return ResponseEntity.ok().body(doacaoservice.getListDoacao().stream()
				.map(doacao -> modelMapper.map(doacao, DoacaoDTO.class)).collect(Collectors.toList()));
	}
	
	@GetMapping("/doacaobydoacaocategoria/{doacaocategoria}")
	public ResponseEntity<?> getListDoacaoPorCategoria(@PathVariable("doacaocategoria") DoacaoCategoria doacaocategoria) {
		return ResponseEntity.ok().body(doacaoservice.getListDoacaoPorCategoria(doacaocategoria).stream()
				.map(doacao -> modelMapper.map(doacao, DoacaoDTO.class)).collect(Collectors.toList()));
	}

	@GetMapping("/doacaobypessoafisica/{id}")
	public ResponseEntity<?> getPessoafisicaDoacao(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(doacaoservice.getListDoacaoByPessoafisica(id).stream()
				.map(doacao -> modelMapper.map(doacao, DoacaoDTO.class)).collect(Collectors.toList()));
	}

	@GetMapping("/doacaobypessoajuridica/{id}")
	public ResponseEntity<?> getPessoajuridicaDoacao(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(doacaoservice.getListDoacaoByPessoaJuridica(id).stream()
				.map(doacao -> modelMapper.map(doacao, DoacaoDTO.class)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<?> saveDoacao(@RequestBody DoacaoDTO doacaoDto) {

		try {
			// convert DTO to entity
			Doacao doacaoRequest = modelMapper.map(doacaoDto, Doacao.class);

			Doacao doacao = doacaoservice.salvarDoacao(doacaoRequest);

			// convert entity to DTO
			DoacaoDTO doacaoResponse = modelMapper.map(doacao, DoacaoDTO.class);

			return new ResponseEntity<DoacaoDTO>(doacaoResponse, HttpStatus.CREATED);

		} catch (Exception ex) {
			throw new ResourceNotFoundException(doacaoDto);

		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getDoacaoById(@PathVariable Integer id) {
		try {
			Doacao doacao = doacaoservice.getDoacaoById(id);

			// convert entity to DTO
			DoacaoDTO doacaoDto = modelMapper.map(doacao, DoacaoDTO.class);
			return new ResponseEntity<>(doacaoDto, HttpStatus.OK);
			
		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteDoacao(@PathVariable Integer id) {
		Doacao doacao = doacaoservice.getDoacaoById(id);
		doacaoservice.deleteDoacao(doacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateDoacao(@Valid @PathVariable Integer id, @RequestBody DoacaoDTO doacaoDto) {
		try {
			// convert DTO to entity
			Doacao doacaoRequest = modelMapper.map(doacaoDto, Doacao.class);
			
			doacaoRequest = doacaoservice.updateDoacao(id, doacaoRequest);

			// convert entity to DTO
			DoacaoDTO doacaoResponse = modelMapper.map(doacaoRequest, DoacaoDTO.class);

			return new ResponseEntity<>(doacaoResponse, HttpStatus.OK);

		} catch (Exception ex) {
			// return new ResponseEntity<>(doacaoDto + " e", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(new ResourceNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
