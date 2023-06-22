package com.senai.ajudame.controller;

import java.util.List;
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
import com.senai.ajudame.dto.PessoaFisicaDTO;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.PessoaFisica;
import com.senai.ajudame.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoafisica")
public class PessoaFisicaController {

	@Autowired
	private ModelMapper modelMapper;

	@Value("${ads04.security.urlManager}")
	private String urlManager;

	@Value("${ads04.security.userManager}")
	private String userManager;

	@Value("${ads04.security.passManager}")
	private String passManager;

	@Autowired
	private PessoaFisicaService pessoafisicaservice;

	@GetMapping
	public ResponseEntity<List<PessoaFisicaDTO>> findAll() {
		return ResponseEntity.ok()
				.body(pessoafisicaservice.getListPessoaFisica().stream()
						.map(pessoafisica -> modelMapper.map(pessoafisica, PessoaFisicaDTO.class))
						.collect(Collectors.toList()));
	}

	@PostMapping("/criar")
	public ResponseEntity<?> savePessoaFisica(@Valid @RequestBody PessoaFisicaDTO pessoafisicaDto) {
		
		User user = new User();
		user.setLogin(pessoafisicaDto.getEmail());
		user.setPassword(pessoafisicaDto.getSenha());
		user.getRoles().add("ROLE_VIEWER");
		user.getExtra().put("tipo", "cpf");
		user.setTenant("teste");
		UtilManager.createUser(urlManager, userManager, passManager, user);
		
		// convert DTO to an entity
		PessoaFisica pessoafisicaRequest = modelMapper.map(pessoafisicaDto, PessoaFisica.class);

		pessoafisicaservice.salvarPessoaFis(pessoafisicaRequest);

		// convert entity to DTO
		PessoaFisicaDTO pessoafisicaDtoResponse = modelMapper.map(pessoafisicaRequest, PessoaFisicaDTO.class);
		return new ResponseEntity<>(pessoafisicaDtoResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPessoaFisicaById(@PathVariable Integer id) {
		try {
			PessoaFisica pessoafisica = pessoafisicaservice.getPessoaFisById(id);

			// convert entity to DTO
			PessoaFisicaDTO pessoafisicaDtoResponse = modelMapper.map(pessoafisica, PessoaFisicaDTO.class);

			return new ResponseEntity<>(pessoafisicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);
			// return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPessoaFisicaByEmail(@PathVariable String email) {
		try {
			PessoaFisica pessoafisica = pessoafisicaservice.getPessoaFisicaByEmail(email);

			// convert entity to DTO
			PessoaFisicaDTO pessoafisicaDtoResponse = modelMapper.map(pessoafisica, PessoaFisicaDTO.class);

			return new ResponseEntity<>(pessoafisicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(email);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePessoaFisica(@PathVariable Integer id) {
		try {
			// VerificaPessoaFisicaExist(id);
			PessoaFisica pessoafisica = pessoafisicaservice.getPessoaFisById(id);

			pessoafisicaservice.deletePessoaFis(pessoafisica);
			// convert entity to DTO
			PessoaFisicaDTO pessoafisicaDtoResponse = modelMapper.map(pessoafisica, PessoaFisicaDTO.class);

			return new ResponseEntity<>(pessoafisicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(id);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePessoaFisica(@Valid @PathVariable Integer id,
			@RequestBody PessoaFisicaDTO pessoafisicaDto) {
		try {
			// convert DTO to an entity
			PessoaFisica pessoafisicaRequest = modelMapper.map(pessoafisicaDto, PessoaFisica.class);

			pessoafisicaRequest = pessoafisicaservice.updatePessoaFis(id, pessoafisicaRequest);

			// convert entity to DTO
			PessoaFisicaDTO pessoafisicaDtoResponse = modelMapper.map(pessoafisicaRequest, PessoaFisicaDTO.class);

			return new ResponseEntity<>(pessoafisicaDtoResponse, HttpStatus.OK);
		} catch (Exception ex) {
			throw new ResourceNotFoundException(pessoafisicaDto);
		}
	}

}
