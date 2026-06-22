package splitWise.models;

import java.util.*;
public class Group {
    private int groupId;
    private HashMap<Integer,User> users;

    public Group(int groupId,HashMap<Integer,User> users){
        this.groupId = groupId;
        this.users =users;
    }

    public int getGroupId(){
        return groupId;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public void addUser(User user){
        users.put(user.getUserId(),user);
    }
}
