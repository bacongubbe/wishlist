package dev.bacongubbe.wishlist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/wishlists")
@RestController
public class WishlistController {
    @GetMapping
    public String getAll() {
        return "Hello, world!";
    }
}
