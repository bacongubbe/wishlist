package dev.bacongubbe.wishlist;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationNotSupportedException;
import java.security.Principal;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class StatusController {

    @GetMapping
    public ResponseEntity<String> getStatus(@AuthenticationPrincipal Jwt user) {
        if (user == null) {
            return ResponseEntity.ok("Hello");
        }
        return ResponseEntity.ok("Hello " + user.getClaims().get("name"));
    }
}
