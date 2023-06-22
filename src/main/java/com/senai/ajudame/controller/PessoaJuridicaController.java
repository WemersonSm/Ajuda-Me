package com.senai.ajudame.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.franciscocalaca.http.auth.User;
import com.franciscocalaca.http.auth.UtilManager;
import com.senai.ajudame.dto.PessoaJuridicaDTO;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.PessoaJuridica;
import com.senai.ajudame.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoajuridica")
public class PessoaJuridicaController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${ads04.security.urlManager}")
	private String urlManager;

	@Value("${ads04.security.userManager}")
	private String userManager;

	@Value("${ads04.security.passManager}")
	private String passManager;

	@Autowired
	private PessoaJuridicaService pessoajuridicaservice;

	@GetMapping
	public ResponseEntity<?> getPessoaJuridica() {

		return ResponseEntity.ok()
				.body(pessoajuridicaservice.getListPessoaJuridica().stream()
						.map(pessoajuridica -> modelMapper.map(pessoajuridica, PessoaJuridicaDTO.class))
						.collect(Collectors.toList()));
	}

	@PostMapping("/criar")
	public ResponseEntity<?> savePessoaJuridica(@Valid @RequestBody PessoaJuridicaDTO pessoajuridicaDto) {
		
		User user = new User();
		user.setLogin(pessoajuridicaDto.getEmail());
		user.setPassword(pessoajuridicaDto.getSenha());
		user.getRoles().add("ROLE_VIEWER");
		user.getExtra().put("tipo", "cnpj");
		user.setTenant("teste");

		UtilManager.createUser(urlManager, userManager, passManager, user);
		
		// convert DTO to an entity
		PessoaJuridica pessoajuridicaRequest = modelMapper.map(pessoajuridicaDto, PessoaJuridica.class);

		pessoajuridicaservice.salvarPessoaJur(pessoajuridicaRequest);

		// convert entity to DTO
		PessoaJuridicaDTO pessoajuridicaDtoResponse = modelMapper.map(pessoajuridicaRequest, PessoaJuridicaDTO.class);
		
		return new ResponseEntity<>(pessoajuridicaDtoResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPessoaJuridicaById(@PathVariable Integer id) {
		try {
			PessoaJuridica pessoajuridicaRequest = pessoajuridicaservice.getPessoaJurById(id);

			// convert entity to DTO
			PessoaJuridicaDTO pessoajuridicaDtoResponse = modelMapper.map(pessoajuridicaRequest, PessoaJuridicaDTO.class);
			
			return new ResponseEntity<>(pessoajuridicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPessoaFisicaByEmail(@PathVariable String email) {
		try {
			PessoaJuridica pessoajuridicaRequest = pessoajuridicaservice.getPessoaJurByEmail(email);

			// convert entity to DTO
			PessoaJuridicaDTO pessoajuridicaDtoResponse = modelMapper.map(pessoajuridicaRequest, PessoaJuridicaDTO.class);

			return new ResponseEntity<>(pessoajuridicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(email);
			// return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePessoaJuridica(@PathVariable Integer id) throws ResourceNotFoundException {
		try {
			PessoaJuridica pessoajuridicaRequest = pessoajuridicaservice.getPessoaJurById(id);

			pessoajuridicaservice.deletePessoaJur(pessoajuridicaRequest);

			// convert entity to DTO
			PessoaJuridicaDTO pessoajuridicaDtoResponse = modelMapper.map(pessoajuridicaRequest, PessoaJuridicaDTO.class);

			return new ResponseEntity<>(pessoajuridicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePessoaJuridica(@Valid @PathVariable Integer id,
			@RequestBody PessoaJuridicaDTO pessoajuridicaDto) {
		try {
			// convert DTO to an entity
			PessoaJuridica pessoajuridicaRequest = modelMapper.map(pessoajuridicaDto, PessoaJuridica.class);
			
			pessoajuridicaRequest = pessoajuridicaservice.updatePessoaJur(id, pessoajuridicaRequest);
			
			// convert entity to DTO
			PessoaJuridicaDTO pessoajuridicaDtoResponse = modelMapper.map(pessoajuridicaRequest, PessoaJuridicaDTO.class);
			
			return new ResponseEntity<>(pessoajuridicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {

			throw new ResourceNotFoundException(pessoajuridicaDto);
		}
	}
}
