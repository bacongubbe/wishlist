package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.user.UserService;
import dev.bacongubbe.wishlistapp.wishlist.dtos.CreateWishlistDto;
import dev.bacongubbe.wishlistapp.wishlist.dtos.OwnerWishlistDto;
import dev.bacongubbe.wishlistapp.wishlist.dtos.WishlistListDto;
import dev.bacongubbe.wishlistapp.wishlist.dtos.WishlistSummaryDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RequestMapping("/api/wishlists")
@RestController
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WishlistSummaryDto> createNewWishlist(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid CreateWishlistDto dto) {
        var owner = userService.getUser(jwt.getSubject());
        var created = service.createWishlist(new Wishlist(dto.name(), owner));
        return ResponseEntity.created(URI.create("/api/wishlists/%s".formatted(created.getId()))).body(new WishlistSummaryDto(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@AuthenticationPrincipal Jwt jwt,
                                               @PathVariable String id){
        var owner = userService.getUser(jwt.getSubject());
        service.deleteWishlist(id, owner);
        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<Void> handleNoSuchElementException(NoSuchElementException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Location", "/login").build();
    }

    @ExceptionHandler(IllegalCallerException.class)
    private ResponseEntity<Void> handleIllegalCallerException(IllegalCallerException ex){
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Message", ex.getMessage()).build();
    }
}
