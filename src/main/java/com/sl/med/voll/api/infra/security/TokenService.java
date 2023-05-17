package com.sl.med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.sl.med.voll.api.domain.usuario.Usuario;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenService {

	public String gerarToken(Usuario usuario) {

		try {
			var algoritmo = Algorithm.HMAC256(System.getenv("MED_VOLL_SECRET"));
			return JWT.create()
					.withIssuer("API Voll.med")
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withExpiresAt(dataExpiracao())
					.sign(algoritmo);
		} catch (JWTCreationException e) {
			log.error(e.getMessage());
			throw new RuntimeException("Erro ao gerar token jwt: {}", e);
		}

	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
