package dev.bacongubbe.wishlistapp.user;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import org.springframework.http.ResponseEntity;
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
}
