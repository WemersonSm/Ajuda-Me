package com.senai.ajudame.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franciscocalaca.http.utils.Token;
import com.senai.ajudame.repository.LoginRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginRepository loginDao;

	@Value("${ads04.security.urlManager}")
	private String urlManager;

	@Value("${ads04.security.userManager}")
	private String userManager;

	@Value("${ads04.security.passManager}")
	private String passManager;

	@PostMapping
	public Map<String, Object> post(@RequestBody Map<String, Object> dados) {

		String login = (String) dados.get("login");
		String senha = (String) dados.get("senha");

		Token token = loginDao.getToken(login, senha);
		String tipo = (String )token.getExtra().get("tipo");
		
		Map<String, Object> resp = new HashMap<>();
		resp.put("access_token", token.getAccessToken());
		resp.put("login", login);
		resp.put("tipo", tipo);

		return resp;
	}
}