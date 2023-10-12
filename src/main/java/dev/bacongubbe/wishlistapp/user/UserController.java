package dev.bacongubbe.wishlistapp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(service.getUser(jwt.getSubject()));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@AuthenticationPrincipal Jwt jwt) {
        User created = service.createUser(new User(jwt.getSubject(), jwt.getClaim("name"), jwt.getClaim("email")));
        return ResponseEntity.created(URI.create(created.getId())).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<Void> handleNoUser(){
        return ResponseEntity.notFound().build();
    }

}
