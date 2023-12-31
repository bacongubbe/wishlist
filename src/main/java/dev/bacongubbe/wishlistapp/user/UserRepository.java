package dev.bacongubbe.wishlistapp.user;

import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class UserRepository {

    private final UserDao dao;

    public UserRepository(UserDao dao){
        this.dao = dao;
    }

    public User getUser(String id) {
        return dao.findById(id).orElseThrow(() -> new NoSuchElementException("No user with id %s".formatted(id)));
    }

    public User createUser(User user){
        return dao.save(user);
    }

    public User getUserForEmail(String email) {
        return dao.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("No user with email: %s".formatted(email)));
    }
}
