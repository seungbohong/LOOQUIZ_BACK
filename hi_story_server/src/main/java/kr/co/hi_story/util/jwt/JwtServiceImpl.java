package kr.co.hi_story.util.jwt;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("jwtService")
public class JwtServiceImpl implements JwtService {

	private static final String SALT = "quiz Secret";
	private final byte[] KEY = this.generateKey();

	@Override
	public <T> String create(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
//						 .setHeaderParam("regDate", System.currentTimeMillis())
				.setSubject(subject).claim(key, data)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24)))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
		return jwt;
	}

	public byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("generateKey error");
		}
		return key;
	}

	@Override
	public boolean isUsable(String jwt) {
		try {
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
//			Date exp = claims.getBody().getExpiration();
//			int compare = now.compareTo(exp);
//			
//			if(compare > 0) {
//				return true;	
//			}else{
//				return false;
//			}
		} catch (Exception e) {
			// 변환 도중 유효기간 지나있으면 나온다.
			
			throw new UnauthorizedException();
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>) claims.getBody().get(key);
		return value;
	}

	@Override
	public String getUserID() {
		try {
			return (String) this.get("userID").get("uid");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public int logout(String key) {
		try {
			if(isUsable(key)) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
						.getRequest();
				String jwt = request.getHeader("Authorization");
				Date now = new Date(System.currentTimeMillis());
				Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
				System.out.println(claims.getBody().setSubject("User").getExpiration());
				System.out.println(claims.getBody().setSubject("User").setExpiration(now));
				System.out.println(claims.getBody().setSubject("User").getExpiration());
				return 1;
			}else {
				return 0;
			}
			
//			System.out.println(claims.getBody());
		}catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

}