package vn.amela.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.amela.entity.User;

@Component
@Slf4j
public class JwtUtil {

    @Value("${duyphan.jwt.expiration}")
    private static int jwtExpiration;

    @Value("${duyphan.jwt.secret}")
    private static String jwtSecret;

    public String genertateToken(User user) {
        Date now = new Date();
        return Jwts.builder().setSubject(String.valueOf(user.getId()))
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception e) {
            log.error("Wrong token");
        }
        return false;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println(e);
        }
        return claims;
    }

    public String getSubjectFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
}
