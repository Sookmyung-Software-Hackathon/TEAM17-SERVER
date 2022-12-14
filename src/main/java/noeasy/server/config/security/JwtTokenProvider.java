package noeasy.server.config.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import noeasy.server.repository.MemberRepository;
import noeasy.server.util.KeyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/*
 * JWT Token 생성, 인증, 권한 부여, 유효성 검사, PK 추출 등의 다양한 기능을 제공하는 Provider 클래스
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${key.secret-key}")
    private String secretKey;

    private long tokenValidTime = 12 * 60 * 60 * 1000L;  // 12시간

    private final UserDetailsService userDetailsService;
    private final MemberRepository memberRepository;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        if(validateToken(token))
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        else return null;
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateTokenExceptExpiration(String jwtToken) {
        try {
            if(validateToken(jwtToken)) return false;
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
