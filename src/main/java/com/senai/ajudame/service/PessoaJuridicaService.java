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
import com.senai.ajudame.model.PessoaJuridica;
import com.senai.ajudame.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoajuridicaRepository;

	public PessoaJuridicaService(PessoaJuridicaRepository pessoajuridicaRepository) {
		super();
		this.pessoajuridicaRepository = pessoajuridicaRepository;
	}

	public List<PessoaJuridica> getListPessoaJuridica() {
		return pessoajuridicaRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PessoaJuridica salvarPessoaJur(PessoaJuridica pessoajuridicaRequest) {
		return pessoajuridicaRepository.save(pessoajuridicaRequest);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PessoaJuridica updatePessoaJur(int id, PessoaJuridica pessoajuridicaRequest) {
		try {
			PessoaJuridica pessoajuridica = pessoajuridicaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(pessoajuridicaRequest));
			pessoajuridica.setNome(pessoajuridicaRequest.getNome());
			pessoajuridica.setTelefone(pessoajuridicaRequest.getTelefone());
			pessoajuridica.setEmail(pessoajuridicaRequest.getEmail());
		//	pessoajuridica.setSenha(pessoajuridicaRequest.getSenha());
			pessoajuridica.setPessoacategoria(pessoajuridicaRequest.getPessoacategoria());
			return pessoajuridicaRepository.save(pessoajuridica);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deletePessoaJur(PessoaJuridica pessoajuridicaRequest) {
		try {
			pessoajuridicaRepository.deleteById(pessoajuridicaRequest.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(pessoajuridicaRequest.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public PessoaJuridica getPessoaJurById(int id) {
		Optional<PessoaJuridica> obj = pessoajuridicaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}

	public PessoaJuridica getPessoaJurByEmail(String email) {
		PessoaJuridica obj = pessoajuridicaRepository.findPjByEmail(email);
		return obj;
	}
}
