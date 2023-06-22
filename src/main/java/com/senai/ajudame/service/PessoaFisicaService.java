package com.senai.ajudame.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ajudame.exception.DatabaseException;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.PessoaFisica;
import com.senai.ajudame.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoafisicaRepository;

	public PessoaFisicaService(PessoaFisicaRepository pessoafisicarepository) {
		super();
		this.pessoafisicaRepository = pessoafisicarepository;
	}

	public List<PessoaFisica> getListPessoaFisica() {
		return pessoafisicaRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PessoaFisica salvarPessoaFis(PessoaFisica pessoafisica) {
		return pessoafisicaRepository.save(pessoafisica);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PessoaFisica updatePessoaFis(int id, PessoaFisica pessoafisicaRequest) {

		try {
			PessoaFisica pessoafisica = pessoafisicaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(pessoafisicaRequest));
			pessoafisica.setNome(pessoafisicaRequest.getNome());
			pessoafisica.setCpf(pessoafisicaRequest.getCpf());
			pessoafisica.setTelefone(pessoafisicaRequest.getTelefone());
			pessoafisica.setEmail(pessoafisicaRequest.getEmail());
		//	pessoafisica.setSenha(pessoafisicaRequest.getSenha());
			pessoafisica.setPessoacategoria(pessoafisicaRequest.getPessoacategoria());
			return pessoafisicaRepository.save(pessoafisica);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(pessoafisicaRequest);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deletePessoaFis(PessoaFisica pessoafisica) {
		try {
			pessoafisicaRepository.deleteById(pessoafisica.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(pessoafisica.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public PessoaFisica getPessoaFisById(int id) {
		Optional<PessoaFisica> obj = pessoafisicaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}
	
	public PessoaFisica getPessoaFisicaByEmail(String email) {
		PessoaFisica obj = pessoafisicaRepository.findByEmail(email);
		return obj;
	}
}
