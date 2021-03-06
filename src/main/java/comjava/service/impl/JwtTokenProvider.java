package comjava.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import comjava.entity.RoleType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${TOKEN_EXPIRED}")
	private long TOKEN_EXPIRED;

	@Value("${TOKEN_SECRET}")
	private String TOKEN_SECRET;

	public String generateToken(Integer userId, RoleType roleType) {

		Date now = new Date();
		System.out.println("TOKEN_EXPIRED: " + TOKEN_EXPIRED);
		System.out.println("TOKEN_SECRET: " + TOKEN_SECRET);
		Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRED);

		return Jwts.builder().setSubject(String.valueOf(userId)).claim("role", roleType.toString()).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	public Integer getUserIdFromJWT(String token) {

		Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
		return Integer.valueOf(claims.getSubject());
	}

	public String getUserRoleFromJWT(String token) {

		Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
		return claims.get("role", String.class);
	}

}
