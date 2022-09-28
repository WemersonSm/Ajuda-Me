package com.senai.ajudame.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.ajudame.dao.PessoaJuridicaDao;
import com.senai.ajudame.entity.PessoaJuridica;

@RestController
@RequestMapping("/pessoajuridica")
public class PessoaJuridicaRest {
	@Autowired
	private PessoaJuridicaDao pessoajuridicaDao;

	@GetMapping
	public List<PessoaJuridica> get() {
		return pessoajuridicaDao.findAll();
	}

	@GetMapping("/{id}")
	public PessoaJuridica get(@PathVariable("id") Integer id) {
		return pessoajuridicaDao.findById(id).get();
	}

	@PostMapping
	public void post(@RequestBody PessoaJuridica pessoajuridica) {
		pessoajuridicaDao.save(pessoajuridica);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		pessoajuridicaDao.deleteById(id);
	}

}
