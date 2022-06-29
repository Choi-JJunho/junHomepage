package junho.homepage.service;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {

    private final String baseKey = "thisisdummykeythisisdummykeythisisdummykeythisisdummykeythisisdummykey";
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private Key createKey() {
        // signiture에 대한 정보는 Byte array로 구성되어있습니다.
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(baseKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public String createJwt(HttpServletRequest request) throws Exception {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("name", request.getParameter("name"));
        claims.put("id", request.getParameter("id"));

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap).setClaims(claims).setExpiration(expireTime).signWith(createKey(), signatureAlgorithm);

        String result = builder.compact();
        System.out.println("serviceTester " + result);
        return result;
    }

    public Boolean checkJwt(String jwt) throws Exception {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(baseKey))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            System.out.println("Id : " + claims.get("id"));
            System.out.println("Name : " + claims.get("name"));
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // for test
    public String createJwt() throws Exception {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("name", "test");
        claims.put("id", "143");

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);


        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(expireTime)
                .signWith(createKey(), signatureAlgorithm);

        String result = builder.compact();
        System.out.println("serviceTester " + result);
        return builder.compact();
    }
}
