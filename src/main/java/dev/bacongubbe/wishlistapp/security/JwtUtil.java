package dev.bacongubbe.wishlistapp.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtUtil {

    private final JwtEncoder encoder = jwtEncoder();

    public String generateToken(OAuth2User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(user.getAttributes().get("sub").toString())
            .claim("name", user.getAttributes().get("name"))
            .claim("email", user.getAttributes().get("email"))
            .build();
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.encoder.encode(encoderParameters).getTokenValue();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>("super_secret_mega_cool_super_duper_key_of_actual_dooooooom_holy_shit".getBytes()));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
            .withSecretKey(new SecretKeySpec("super_secret_mega_cool_super_duper_key_of_actual_dooooooom_holy_shit".getBytes(), "RSA"))
            .macAlgorithm(MacAlgorithm.HS256)
            .build();
    }
}
