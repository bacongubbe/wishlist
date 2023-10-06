package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.NoSuchElementException;

@RequestMapping("/api/wishlists")
@RestController
@CrossOrigin(origins = "*")
public class WishlistController {

    private final WishlistService service;
    private final UserService userService;

    public WishlistController(WishlistService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<WishlistListDto> getAll() {
        return ResponseEntity.ok(new WishlistListDto(Collections.emptyList()));
    }

    @PostMapping
    public ResponseEntity<Void> createNewWishlist(@AuthenticationPrincipal Jwt jwt, @RequestBody String name) {
        User owner = userService.getUser(jwt.getSubject());
        var created = service.createWishlist(new Wishlist(name, owner));
        return ResponseEntity.created(URI.create("/api/wishlists/%s".formatted(created.getId()))).build();
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Location", "/login").build();
    }
}
