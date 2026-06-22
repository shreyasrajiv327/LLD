package splitWise.repository;

import splitWise.models.*;
import java.util.*;

public class expenseRepository {
    private final HashMap<Integer,Expense> expenseRepository;

    public expenseRepository(){
        this.expenseRepository = new HashMap<>();
    }
    
    public void addExpense(Expense expense){
        expenseRepository.put(expense.getExpenseId(),expense);
    }

    public Expense getExpense(int expenseId){
        if(expenseRepository.containsKey(expenseId)){
            return expenseRepository.get(expenseId);
        }
        return null;
    }

    public boolean expenseExists(int expenseId){
         if(expenseRepository.containsKey(expenseId)){
            return true;
        }
        return false;
    }

}
