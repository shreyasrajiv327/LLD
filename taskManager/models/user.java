package models;
import java.util.*;
public class user {
    public String name;
    public int userID;

    public user(String name, int userID){
        this.name = name;
        this.userID = userID;
    }

    public int getUserId(){
        return userID;
    }

    public String getUserName(){
        return name;
    }

}
