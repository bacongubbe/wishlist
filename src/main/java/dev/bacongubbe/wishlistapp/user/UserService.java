package dev.bacongubbe.wishlistapp.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public User getUser(String id) {
        return repo.getUser(id);
    }

    public User createUser(User user){
        return repo.createUser(user);
    }
}
