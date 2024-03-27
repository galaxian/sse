package com.example.sse.common;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private static final String TYPE_CLAIM_KEY = "type";

	private final String secretKey;
	private final Long tokenValidity;

	public JwtProvider(@Value("${jwt.secret}") String secretKey,
		@Value("${jwt.token-validity}") Long tokenValidity) {
		this.secretKey = secretKey;
		this.tokenValidity = tokenValidity;
	}

	public String createAccessToken(Long subject, Map<String, Object> claims) {
		Map<String, Object> copiedClaims = new HashMap<>(claims);
		copiedClaims.put(TYPE_CLAIM_KEY, "Access");
		return createToken(tokenValidity, subject, copiedClaims);
	}

	private String createToken(Long tokenValidity, Long subject, Map<String, Object> claims) {
		SecretKey signKey = createKey();
		Date expiration = createExpiration(tokenValidity);
		return Jwts.builder()
			.signWith(signKey)
			.setClaims(claims)
			.setSubject(String.valueOf(subject))
			.setExpiration(expiration)
			.compact();
	}

	private Date createExpiration(Long validity) {
		long now = new Date().getTime();
		return new Date(now + validity);
	}

	private SecretKey createKey() {
		byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(secretKeyBytes);
	}

	public boolean isValidToken(String token) {
		try {
			parseClaimsJws(token);
		} catch (ExpiredJwtException expiredJwtException) {
			throw new RuntimeException("");
		} catch (JwtException jwtException) {
			throw new RuntimeException("");
		}
		return true;
	}

	private Jws<Claims> parseClaimsJws(String token) {
		SecretKey signKey = createKey();
		return Jwts.parserBuilder()
			.setSigningKey(signKey)
			.build()
			.parseClaimsJws(token);
	}

	public String findSubject(String token) {
		return (parseClaimsJws(token)
			.getBody()
			.getSubject());
	}
}
