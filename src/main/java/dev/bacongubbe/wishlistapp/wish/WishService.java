package dev.bacongubbe.wishlistapp.wish;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.wishlist.WishlistRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishRepo repo;

    private WishlistRepo wishlistRepo;

    public WishService(WishRepo repo, WishlistRepo wishlistRepo) {
        this.repo = repo;
        this.wishlistRepo = wishlistRepo;
    }

    public Wish addWish(Wish wish) {
        return repo.addWish(wish);
    }

    public void deleteWish(String id, User owner) {
        var wish = repo.getWishById(id);
        var wishlist = wish.getWishlist();
        if (!wishlist.getOwner().equals(owner)){
            throw new IllegalCallerException("You are not the owner of this wish");
        }
        repo.delete(wish);
    }
}
