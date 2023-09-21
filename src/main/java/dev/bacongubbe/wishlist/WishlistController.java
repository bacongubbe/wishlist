package dev.bacongubbe.wishlist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/api/wishlists")
@RestController
@CrossOrigin(origins = "*")
public class WishlistController {
    @GetMapping
    public ResponseEntity<WishlistListDto> getAll() {
        return ResponseEntity.ok(new WishlistListDto(Collections.emptyList()));
    }
}
