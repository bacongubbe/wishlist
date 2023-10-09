package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class WishlistRepo {

    private final WishlistDao repo;

    public WishlistRepo(WishlistDao repo) {
        this.repo = repo;
    }

    public Wishlist saveWishlist(Wishlist wishlist) {
        return repo.save(wishlist);
    }

    public List<Wishlist> getWishlistsForUser(User owner) {
        return repo.findByOwner(owner);
    }

    public Wishlist getWishlistForOwner(User owner, String wishlistId) {
        return repo.findByOwnerAndId(owner, wishlistId)
            .orElseThrow(() -> new NoSuchElementException("Wishlist %s for user %s not found".formatted(wishlistId, owner.getName())));
    }
}
