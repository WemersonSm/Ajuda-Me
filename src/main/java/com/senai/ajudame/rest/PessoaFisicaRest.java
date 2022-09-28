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
import com.senai.ajudame.dao.PessoaFisicaDao;
import com.senai.ajudame.entity.PessoaFisica;

@RestController
@RequestMapping("/pessoafisica")
public class PessoaFisicaRest {
	@Autowired
	private PessoaFisicaDao pessoafisicaDao;

	@GetMapping
	public List<PessoaFisica> get() {
		return pessoafisicaDao.findAll();
	}

	@GetMapping("/{id}")
	public PessoaFisica get(@PathVariable("id") Integer id) {
		return pessoafisicaDao.findById(id).get();
	}

	@PostMapping
	public void post(@RequestBody PessoaFisica pessoafisica) {
		pessoafisicaDao.save(pessoafisica);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		pessoafisicaDao.deleteById(id);
	}

}
