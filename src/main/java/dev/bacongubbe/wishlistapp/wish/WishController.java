package dev.bacongubbe.wishlistapp.wish;

import dev.bacongubbe.wishlistapp.user.UserService;
import dev.bacongubbe.wishlistapp.wish.dtos.CreateWishDto;
import dev.bacongubbe.wishlistapp.wish.dtos.OwnerWishDto;
import dev.bacongubbe.wishlistapp.wishlist.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;
    private final WishlistService wishlistService;
    private final UserService userService;

    @Autowired
    public WishController(WishService wishService, WishlistService wishlistService, UserService userService) {
        this.wishService = wishService;
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<OwnerWishDto> addWish(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody @Valid CreateWishDto dto
    ) {
        var owner = userService.getUser(jwt.getSubject());
        var list = wishlistService.getWishlistForOwner(owner, dto.wishlistId());
        var created = wishService.addWish(new Wish(UUID.randomUUID().toString(), dto.wish(), dto.link(), null, list));
        return ResponseEntity.created(URI.create("/api/wishes/%s".formatted(created.getId()))).body(new OwnerWishDto(created));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWish(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable String id){
        var owner = userService.getUser(jwt.getId());
        wishService.deleteWish(id, owner);
        return ResponseEntity.noContent().build();
    }
}
