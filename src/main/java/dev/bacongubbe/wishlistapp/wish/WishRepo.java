package dev.bacongubbe.wishlistapp.wish;

import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class WishRepo {

    private final WishDao dao;

    public WishRepo(WishDao dao) {
        this.dao = dao;
    }

    public Wish getWishById(String id) {
        return dao.findById(id)
            .orElseThrow(() -> new NoSuchElementException("No Wish with ID: %s".formatted(id)));
    }


    public Wish addWish(Wish wish) {
        return dao.save(wish);
    }

    public void delete(Wish wish) {
        dao.delete(wish);
    }
}
