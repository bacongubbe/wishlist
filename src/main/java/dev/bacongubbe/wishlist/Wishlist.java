package dev.bacongubbe.wishlist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
public class Wishlist {

    private String id;

    private User owner;
    private List<User> subscribers;

    private List<Wish> wishes;

}
