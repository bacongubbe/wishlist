package dev.bacongubbe.wishlistapp.wishlist;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequestMapping("/api/wishlists")
@RestController
@CrossOrigin(origins = "*")
public class WishlistController {


    @GetMapping
    public ResponseEntity<WishlistListDto> getAll() {
        return ResponseEntity.ok(new WishlistListDto(Collections.emptyList()));
    }

    @PostMapping
    public ResponseEntity<Void> createNewWishlist(@AuthenticationPrincipal Jwt jwt,@RequestBody String name) {



        return null;
    }
}
