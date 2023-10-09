package dev.bacongubbe.wishlistapp.wish;

import org.springframework.stereotype.Repository;

@Repository
public class WishRepo {

    private final WishDao dao;

    public WishRepo(WishDao dao) {
        this.dao = dao;
    }


    public Wish addWish(Wish wish) {
        return dao.save(wish);
    }
}
