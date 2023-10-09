package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import org.springframework.stereotype.Repository;

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

    public WishlistListDto getWishlistsForUser(User owner) {
        return new WishlistListDto(repo.findByOwner(owner));
    } // TODO: Make sure this is not a DTO

    public Wishlist getWishlistForOwner(User owner, String wishlistId) {
        return repo.findByOwnerAndId(owner, wishlistId)
            .orElseThrow(() -> new NoSuchElementException("Wishlist %s for user %s not found".formatted(wishlistId, owner.getName())));
    }
}
