package com.sl.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.med.voll.api.domain.usuario.DadosAtutenticacao;
import com.sl.med.voll.api.domain.usuario.Usuario;
import com.sl.med.voll.api.infra.security.TokenService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("login")
@Slf4j
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAtutenticacao dados) {

		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

		log.info("User Log: {}", dados.login());

		var authentication = manager.authenticate(token);

		log.info("User Logged: {}", dados.login());

		return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));

	}
}
