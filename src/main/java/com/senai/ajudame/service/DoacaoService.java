package com.senai.ajudame.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ajudame.enums.DoacaoCategoria;
import com.senai.ajudame.exception.ResourceNotFoundException;
import com.senai.ajudame.model.Doacao;
import com.senai.ajudame.repository.DoacaoRepository;

@Service
public class DoacaoService {

	@Autowired
	private DoacaoRepository doacaoRepository;

	@Autowired
	private PessoaFisicaService pessoafisicaservice;

	@Autowired
	private PessoaJuridicaService pessoajuridicaservice;

	public DoacaoService(DoacaoRepository doacaoRepository) {
		super();
		this.doacaoRepository = doacaoRepository;
	}

	public List<Doacao> getListDoacao() {
		return doacaoRepository.findAll();
	}
	
	public List<Doacao> getListDoacaoPorCategoria(DoacaoCategoria doacaocategoria) {
		return doacaoRepository.findByDoacaocategoria(doacaocategoria);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Doacao salvarDoacao(Doacao doacao) {
		return doacaoRepository.save(doacao);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Doacao updateDoacao(int id, Doacao doacaoRequest) {
		try {
			Doacao doacao = doacaoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(doacaoRequest));
			doacao.setDescricao(doacaoRequest.getDescricao());
			doacao.setData(doacaoRequest.getData());
			doacao.setTelefone(doacaoRequest.getTelefone());
			doacao.setDoacaocategoria(doacaoRequest.getDoacaocategoria());
			doacao.setBairro(doacaoRequest.getBairro());
			doacao.setCep(doacaoRequest.getCep());

			if (doacaoRequest.getFisica() != null && doacaoRequest.getFisica().getId() != null) {
				doacao.setFisica(pessoafisicaservice.getPessoaFisById(doacaoRequest.getFisica().getId()));
			}

			if (doacaoRequest.getJuridica() != null && doacaoRequest.getJuridica().getId() != null) {
				doacao.setJuridica(pessoajuridicaservice.getPessoaJurById(doacaoRequest.getJuridica().getId()));
			}
			
			return doacaoRepository.save(doacao);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(doacaoRequest);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDoacao(Doacao doacao) {
		try {
			doacaoRepository.deleteById(doacao.getId());
		} catch (Exception e) {
			throw new RuntimeException(String.format("Erro ao excluir a doação %d: %s", doacao.getId(), e.getMessage()));
		}
	}

	public Doacao getDoacaoById(int id) {
		Optional<Doacao> obj = doacaoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}
	
	public List<Doacao>  getListDoacaoByPessoafisica(int id) {
		List<Doacao> obj = doacaoRepository.getListFisica(id);
		return obj;
	}

	public List<Doacao>  getListDoacaoByPessoaJuridica(int id) {
		List<Doacao> obj = doacaoRepository.getListJuridica(id);
		return obj;
	}
}
