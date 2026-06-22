package splitWise.repository;

import splitWise.models.*;
import java.util.*;

public class groupRepository {
    private final HashMap<Integer,Group> groupRepository;

    public groupRepository(){
        this.groupRepository = new HashMap<>();
    }

    public void addUserToGroup(int groupId,Group group){
        groupRepository.put(groupId,group);
    }

    public Group getGroup(int groupId){
        if(groupRepository.containsKey(groupId)){
            return groupRepository.get(groupId);
        }
        return null;
    }

    public boolean groupExists(int groupId){
        if(groupRepository.containsKey(groupId)){
            return true;
        }
        return false;
    }


}
