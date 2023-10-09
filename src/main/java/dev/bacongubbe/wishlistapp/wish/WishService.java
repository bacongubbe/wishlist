package dev.bacongubbe.wishlistapp.wish;

import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishRepo repo;

    public WishService(WishRepo repo) {
        this.repo = repo;
    }

    public Wish addWish(Wish wish) {
        return repo.addWish(wish);
    }

}
