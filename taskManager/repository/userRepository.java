package repository;
import models.*;
import java.util.*;
public class userRepository {
    public final HashMap<Integer,user> Users;

    public userRepository(){
        this.Users = new HashMap<>();
    }


    public void addUser(user User){
        Users.put(User.getUserId(),User);
    }

    public user getUser(int userId){
        return Users.get(userId);
    }

    public boolean userExists(int userId){
        return Users.containsKey(userId);
    }

    public List<user> getAllUsers(){
        return new ArrayList<>(Users.values());
    }
}
