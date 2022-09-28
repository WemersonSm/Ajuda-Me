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
import com.senai.ajudame.dao.DoacaoDao;
import com.senai.ajudame.entity.Doacao;

@RestController
@RequestMapping("/doacao")
public class DoacaoRest {
	@Autowired
	private DoacaoDao doacaoDao;

	@GetMapping
	public List<Doacao> get() {
		return doacaoDao.findAll();
	}

	@GetMapping("/{id}")
	public Doacao get(@PathVariable("id") Integer id) {
		return doacaoDao.findById(id).get();
	}

	@PostMapping
	public void post(@RequestBody Doacao doacao) {
		doacaoDao.save(doacao);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		doacaoDao.deleteById(id);
	}
}
