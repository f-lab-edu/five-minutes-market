package kr.fiveminutesmarket.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import kr.fiveminutesmarket.user.domain.RoleType;
import kr.fiveminutesmarket.user.domain.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 토큰 암호화 키
    private Date tokenExpirationMs = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)); // 토큰 만료시간

    public String createToken(User user) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(header)
                .setSubject(user.getEmail())
                .claim("seller", user.getSeller())
                .claim("role", user.getRoleType())
                .setExpiration(tokenExpirationMs)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsToken(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            throw new ExpiredJwtException(expiredJwtException.getHeader(),
                    expiredJwtException.getClaims(),
                    "토큰이 만료되었습니다.");
        } catch (JwtException jwtException) {
            throw new JwtException("토큰이 변조 되었습니다.");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("빈 값을 사용할 수 없습니다.");
        }
    }

    public String getEmail(String token) {
        Claims claims = getClaimsToken(token);
        return claims.getSubject();
    }

    public RoleType getRoleType(String token) {
        Claims claims = getClaimsToken(token);
        return claims.get("role", RoleType.class);
    }

    public Boolean getSeller(String token) {
        Claims claims = getClaimsToken(token);
        return claims.get("seller", Boolean.class);
    }

    public Date getExpiredDate(String token) {
        Claims claims = getClaimsToken(token);
        return claims.getExpiration();
    }

    private Claims getClaimsToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
