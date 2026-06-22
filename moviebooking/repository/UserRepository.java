package moviebooking.repository;

import moviebooking.models.User;
import java.util.*;

public class UserRepository {

    private final Map<Integer, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public boolean userExists(int userId) {
        return users.containsKey(userId);
    }
}