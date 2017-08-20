package com.zcc.common.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.zcc.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by NCP-620 on 2017/8/17.
 */
@Component
public class JwtTokenUtil implements Serializable{


	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";

	static final String AUDIENCE_UNKNOWN = "unknown";
	static final String AUDIENCE_WEB = "web";
	static final String AUDIENCE_MOBILE = "mobile";
	static final String AUDIENCE_TABLET = "tablet";

	@Value("${zcc.secret}")
	private String secret;

	@Autowired
	private TimeProvider timeProvider;

	@Value("${zcc.expiration}")
	private Long expiration;

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}


	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(timeProvider.now());
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getCreatedDateFromToken(token);
		//final Date expiration = getExpirationDateFromToken(token);
		return (
			username.equals(user.getUsername())
				&& !isTokenExpired(token)
				&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
	}

	public String generateToken(UserDetails userDetails, Device device) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));

		final Date createdDate = timeProvider.now();
		claims.put(CLAIM_KEY_CREATED, createdDate);

		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

		System.out.println("doGenerateToken " + createdDate);

		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	private String generateAudience(Device device) {
		String audience = AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}
}
