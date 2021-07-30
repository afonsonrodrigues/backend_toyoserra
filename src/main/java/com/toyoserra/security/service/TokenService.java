package com.toyoserra.security.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.toyoserra.models.Perfil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private Long expiration = 315410000000000L;
	private String secret = "minha senha";

	public String generateToken(Authentication authentication) {

		Perfil usuario = (Perfil) authentication.getPrincipal();

		Date now = new Date();
		Date exp = new Date(now.getTime() + expiration);

		return Jwts.builder().setIssuer("IRS").setSubject(usuario.getId().toString()).setIssuedAt(new Date())
				.setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.valueOf(body.getSubject());
	}
		
}
