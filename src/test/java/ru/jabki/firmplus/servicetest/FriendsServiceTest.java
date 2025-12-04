package ru.jabki.firmplus.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.jabki.firmplus.exception.FriendException;
import ru.jabki.firmplus.exception.UserException;
import ru.jabki.firmplus.model.Friend;
import ru.jabki.firmplus.model.User;
import ru.jabki.firmplus.service.FriendsService;
import ru.jabki.firmplus.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendsServiceTest {
    private FriendsService friendsService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.addUser(
                    new User("Петров П.П.",
                            "petrov1971@mail.ru",
                            "petrov1971",
                            LocalDate.of(1971, 3, 2)
                    )
        );
        userService.addUser(
                    new User("Сидоров С.С.",
                            "sidorov1974@mail.ru",
                            "sidorov1974",
                            LocalDate.of(1974, 12, 8)
                    )
        );
        userService.addUser(
                new User("Иванов И.И",
                        "ivanov1980@mail.ru",
                        "ivanov1980",
                        LocalDate.of(1974, 12, 8)
                )
        );
        friendsService = new FriendsService();
        friendsService.addFriend(userService.getbyId(1L).getId(), userService.getbyId(2L).getId());
    }

    @Test
    void testAddFriend() {

        Friend friend = friendsService.addFriend(1L, 2L);
        assertEquals(1, friend.getUserId());
        assertEquals(2, friend.getFriendId());
    }

    @Test
    void testAddFriendValidation() {
        friendsService.addFriend(1L, 2L);
        assertThrows(FriendException.class, () -> friendsService.addFriend(1L, 2L));
        assertThrows(FriendException.class, () -> friendsService.addFriend(1L, 1L));
        assertThrows(UserException.class, () -> friendsService.addFriend(1L, 99L));
    }

    @Test
    void testGetFriends() {
        friendsService.addFriend(1L, 2L);
        friendsService.addFriend(1L, 3L);
        List<Friend> friends = friendsService.getFriend(1L);
        assertEquals(2, friends.size());
        assertTrue(friends.stream().anyMatch(u -> u.getFriendId().equals(2L)));
        assertTrue(friends.stream().anyMatch(u -> u.getFriendId().equals(3L)));
        friends = friendsService.getFriend(2L);
        assertTrue(friends.stream().anyMatch(u -> u.getFriendId().equals(1L)));
    }

    @Test
    void testRemoveFriend() {
        friendsService.addFriend(1L, 2L);
        friendsService.addFriend(1L, 3L);
        friendsService.delete(1L, 2L);
        List<Friend> friends = friendsService.getFriend(1L);
        assertEquals(1, friends.size());
        assertTrue(friends.stream().anyMatch(u -> u.getFriendId().equals(3L)));
        assertThrows(FriendException.class, () -> friendsService.delete(1L, 2L));
    }
}
