package com.sosyalmedya.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1- Kullanıcı kendini doğrulamalı. Username ve password ile.
     * 2- Doğrulanmış kişinin kimlik bilgileri ile ona yeni bir JWT oluşturmalıyız.
     */
    private final String tokenPassword="apşjr932ujprfa-*2352uı3jpas09ıfd2*342";
    private final long exDate=1000L*20;//20 sec
    public Optional<String> createToken(Long id) {
        try {
            String token;
            token= JWT.create()
                    .withAudience()
                    // Dikkat! Lütfen buraya kişisel bilgileri ya da gizli bilgileri eklemeyiniz(Örneğin şifreler,mail,adres)
                    .withIssuer("ugur")
                    .withClaim("id",id)
                    .withClaim("işlem türü","genel")
                    .withIssuedAt(new Date()) // JWT tokenin oluşturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate)) // JWT tokenin geçerlilik zamanı
                    .sign(Algorithm.HMAC512(tokenPassword));
            return Optional.of(token);

        }catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Long> getByIdFromToken(String token) {
        try {
            Algorithm algorithm=Algorithm.HMAC512(tokenPassword);
            JWTVerifier verifier=JWT.require(algorithm)
                    .withIssuer("ugur")
                    .build();
            DecodedJWT decodedJWT=verifier.verify(token);
            if (decodedJWT==null) {
                return Optional.empty();
            }
            Long id=decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}
