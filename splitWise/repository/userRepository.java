package splitWise.repository;
import java.util.HashMap;

import splitWise.models.*;
public class userRepository {
    private final HashMap<Integer,User> userRepository;
    public userRepository(){
        this.userRepository = new HashMap<>();
    }

    public void addUser(User user){
        userRepository.put(user.getUserId(),user);
    }

    public User getUser(int userId){
         if(userRepository.containsKey(userId)){
            return userRepository.get(userId);
         }
         return null;
    }

    public boolean userExists(int userId){
         if(userRepository.containsKey(userId)){
            return true;
         }
         return false;
    }

}
