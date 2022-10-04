package com.jdc.pos.security;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {

	@Value("${app.jwt.issuer}")
	private String issuer;
	@Value("${app.jwt.limit}")
	private int limit;

	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public Optional<String> generate(Authentication auth) {

		if (null != auth && auth.isAuthenticated()) {

			if (auth instanceof UsernamePasswordAuthenticationToken usernamePassword) {
				var expireAt = Calendar.getInstance();
				expireAt.add(Calendar.MINUTE, limit);

				var token = Jwts.builder().setIssuer(issuer).setIssuedAt(new Date())
						.setSubject(usernamePassword.getName()).claim("role", usernamePassword.getAuthorities().stream()
								.map(a -> a.getAuthority()).collect(Collectors.joining(",")))
						.signWith(key).compact();

				return Optional.ofNullable(token);

			}
		}

		return Optional.empty();
	}

	public void authenticate(String token) {
		if (StringUtils.hasLength(token)) {
			var jws = Jwts.parserBuilder().requireIssuer(issuer).setSigningKey(key).build().parseClaimsJws(token);
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(jws.getBody().getSubject(), null,
							AuthorityUtils.commaSeparatedStringToAuthorityList(jws.getBody().get("role").toString())));
		}
	}
}
