package dev.bacongubbe.wishlistapp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(@AuthenticationPrincipal Jwt jwt){

        return ResponseEntity.ok(service.getUser(jwt.getId()));
    }

}
