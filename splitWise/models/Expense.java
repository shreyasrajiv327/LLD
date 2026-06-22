package splitWise.models;
import java.util.*;

public class Expense {
    private double amount;
    private HashMap<Integer,User> participants;
    private int groupId;
    private int expenseId;
    private int userId;

    public Expense(double amount,HashMap<Integer,User> participants,int groupId,int expenseId, int userId){
        this.amount = amount;
        this.participants = participants;
        this.groupId = groupId;
        this.expenseId = expenseId;
        this.userId = userId;
    }

    public double getAmount(){
        return amount;
    }

    public List<User> getParticipants(){
        return new ArrayList<>(participants.values());
    }

    public int getGroupId(){
        return groupId;
    }

    public int getExpenseId(){
        return expenseId;
    }

    public int getUserId(){
        return userId;
    }
}
