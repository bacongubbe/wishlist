package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.user.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RequestMapping("/api/wishlists")
@RestController
@CrossOrigin(origins = "*")
public class WishlistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistController.class);
    private final WishlistService service;
    private final UserService userService;

    public WishlistController(WishlistService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<WishlistListDto> getAllForOwner(@AuthenticationPrincipal Jwt jwt) {
        User owner = userService.getUser(jwt.getSubject());
        return ResponseEntity.ok(new WishlistListDto(service.getWishlistsForUser(owner)
            .stream()
            .map(WishlistSummaryDto::new)
            .toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerWishlistDto> getWishlist(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        User owner = userService.getUser(jwt.getSubject());
        return ResponseEntity.ok(new OwnerWishlistDto(service.getWishlistForOwner(owner, id)));
    }

    @PostMapping
    public ResponseEntity<Void> createNewWishlist(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid CreateWishlistDto dto) {
        User owner = userService.getUser(jwt.getSubject());
        var created = service.createWishlist(new Wishlist(dto.name(), owner));
        return ResponseEntity.created(URI.create("/api/wishlists/%s".formatted(created.getId()))).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<Void> handleNoSuchElementException(NoSuchElementException e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Location", "/login").build();
    }
}
