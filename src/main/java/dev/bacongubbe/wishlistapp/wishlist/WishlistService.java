package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.wish.Wish;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final WishlistRepo repo;

    public WishlistService(WishlistRepo repo) {
        this.repo = repo;
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return repo.saveWishlist(wishlist);
    }

    public WishlistListDto getWishlistsForUser(User owner) {
        return repo.getWishlistsForUser(owner);
    }

    public Wishlist getWishlistForOwner(User owner, String WishlistId) {
        return repo.getWishlistForOwner(owner, WishlistId);
    }
}
