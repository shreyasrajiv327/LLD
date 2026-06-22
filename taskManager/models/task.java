package models;

import java.util.*;
enum taskStatus {
    TO_DO,
    IN_PROGRESS,
    COMPLETED
}

public class task{

    public int taskID;
    public int assignedUserId;

    public task(int taskID, int assignedUserId){
        this.taskID = taskID;
        this.assignedUserId = assignedUserId;
    }

    public int getTaskID(){
        return taskID;
    }

    public int getAssignedUserId(){
        return assignedUserId;
    }
}