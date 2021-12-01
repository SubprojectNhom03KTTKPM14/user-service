package comjava.service.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private final long TOKEN_EXPIRED = 864000000;
	private final String TOKEN_SECRET = "KTKTPM14";

	public String generateToken(Integer userId) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRED);

		return Jwts.builder().setSubject(userId + "").setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	public Integer getUserIdFromJWT(String token) {

		Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
		return Integer.valueOf(claims.getSubject());
	}
}
