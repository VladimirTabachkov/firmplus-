package ru.jabki.firmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.firmplus.exception.FilmException;
import ru.jabki.firmplus.exception.FriendException;
import ru.jabki.firmplus.model.Friend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FriendsService {
    private final UserService userService;
    private List<Friend> friendList;

    public FriendsService(UserService userService) {
        this.friendList = new ArrayList<>();
        this.userService = userService;
    }

    public Friend addFriend(Long userId, Long friendId) {
        validate(userId, friendId);

        Friend friendEx = getByUserIdAndFriendId(userId, friendId);
        if (friendEx != null) {
            return friendEx;
        }

        Friend friend = new Friend(userId, friendId);
        friendList.add(friend);
        return friend;
    }

    public void delete(Long userId, Long friendId) {
        friendList.remove(new Friend(userId, friendId));
    }

    public List<Friend> getFriend(Long friend) {
        return friendList.stream().filter(f -> (!(friend == null) && (Objects.equals(f.getUserId(), friend) || Objects.equals(f.getFriendId(), friend)))).toList();
    }

    private void validate(Long userId, Long friendId) {
        if ((userId == null) || (userService.getbyId(userId) == null)) {
            throw new FriendException("User not found");
        }

        if ((friendId == null) || (userService.getbyId(friendId) == null)) {
            throw new FriendException("Friend not found");
        }

        if (userId == friendId) {
            throw new FriendException("User and Friend are equal");
        }
    }

    public Friend getByUserIdAndFriendId(final Long userId, final Long friendId) {
        return friendList.stream()
                .filter(f -> (f.getUserId() == userId) && (f.getFriendId() == friendId))
                .findFirst()
                .orElse(null);
    }

}