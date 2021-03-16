package project.economy_site.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTProvider {
//    private final UserdetailService userdetailService;

    private String secretKey = "my_secret_key";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60;


    // 객체 초기화 및 secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public String createToken(String userId, List<String> roles) {
        // JWT payload에 저장되는 정보 단위
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 만료 시간 세팅
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화할 알고리즘과 시크릿키 조합
                .compact();
    }
}
