package splitWise.services;

import splitWise.models.*;
import splitWise.repository.*;

import java.util.*;

public class splitWiseService {

    private final userRepository userRepository;
    private final groupRepository groupRepository;
    private final expenseRepository expenseRepository;

    // debtorId -> creditorId -> Balance
    private final HashMap<Integer,
            HashMap<Integer, Balance>> balanceRecord;

    private int userIdCounter;
    private int groupIdCounter;
    private int expenseIdCounter;

    public splitWiseService() {

        this.userRepository = new userRepository();
        this.groupRepository = new groupRepository();
        this.expenseRepository = new expenseRepository();

        this.balanceRecord = new HashMap<>();

        this.userIdCounter = 1;
        this.groupIdCounter = 1;
        this.expenseIdCounter = 1;
    }

    // ----------------------------
    // USER OPERATIONS
    // ----------------------------

    public User addUser(String name) {

        User user =
                new User(name, userIdCounter++);

        userRepository.addUser(user);

        return user;
    }

    // ----------------------------
    // GROUP OPERATIONS
    // ----------------------------

    public Group createGroup() {

        Group group =
                new Group(
                        groupIdCounter++,
                        new HashMap<>()
                );

        groupRepository.addUserToGroup(
                group.getGroupId(),
                group
        );

        return group;
    }

    public void addUserToGroup(
            int groupId,
            int userId) {

        Group group =
                groupRepository.getGroup(groupId);

        User user =
                userRepository.getUser(userId);

        if(group == null) {
            throw new IllegalArgumentException(
                    "Group not found"
            );
        }

        if(user == null) {
            throw new IllegalArgumentException(
                    "User not found"
            );
        }

        group.addUser(user);
    }

    // ----------------------------
    // EXPENSE OPERATIONS
    // ----------------------------

    public Expense addExpense(
            int groupId,
            int paidByUserId,
            double amount) {

        Group group =
                groupRepository.getGroup(groupId);

        if(group == null) {
            throw new IllegalArgumentException(
                    "Group not found"
            );
        }

        Expense expense =
                new Expense(
                        amount,
                        convertToMap(
                                group.getAllUsers()
                        ),
                        groupId,
                        expenseIdCounter++,
                        paidByUserId
                );

        expenseRepository.addExpense(
                expense
        );

        createSplit(expense);

        return expense;
    }

    // ----------------------------
    // SPLIT LOGIC
    // ----------------------------

    private void createSplit(
            Expense expense) {

        int paidBy =
                expense.getUserId();

        List<User> participants =
                expense.getParticipants();

        double share =
                expense.getAmount()
                        / participants.size();

        for(User user : participants) {

            if(user.getUserId()
                    == paidBy) {
                continue;
            }

            int debtorId =
                    user.getUserId();

            balanceRecord.putIfAbsent(
                    debtorId,
                    new HashMap<>()
            );

            if(balanceRecord
                    .get(debtorId)
                    .containsKey(paidBy)) {

                Balance existing =
                        balanceRecord
                                .get(debtorId)
                                .get(paidBy);

                existing.updateBalance(
                        share
                );

            } else {

                Balance balance =
                        new Balance(
                                debtorId,
                                paidBy,
                                share
                        );

                balanceRecord
                        .get(debtorId)
                        .put(
                                paidBy,
                                balance
                        );
            }
        }
    }

    // ----------------------------
    // PAYMENT
    // ----------------------------

    public void payDebt(
            int debtorId,
            int creditorId,
            double amountPaid) {

        if(!balanceRecord.containsKey(
                debtorId)) {

            throw new IllegalArgumentException(
                    "No debt found"
            );
        }

        Balance balance =
                balanceRecord
                        .get(debtorId)
                        .get(creditorId);

        if(balance == null) {

            throw new IllegalArgumentException(
                    "No debt found"
            );
        }

        double remaining =
                balance.getAmount()
                        - amountPaid;

        if(remaining <= 0) {

            balanceRecord
                    .get(debtorId)
                    .remove(creditorId);

        } else {

            balanceRecord
                    .get(debtorId)
                    .put(
                            creditorId,
                            new Balance(
                                    debtorId,
                                    creditorId,
                                    remaining
                            )
                    );
        }
    }

    // ----------------------------
    // SHOW BALANCES
    // ----------------------------

    public void showBalances() {

        for(Integer debtorId :
                balanceRecord.keySet()) {

            for(Balance balance :
                    balanceRecord
                            .get(debtorId)
                            .values()) {

                User debtor =
                        userRepository
                                .getUser(
                                        balance.getDebitorId()
                                );

                User creditor =
                        userRepository
                                .getUser(
                                        balance.getCreditorId()
                                );

                System.out.println(
                        debtor.getName()
                        + " owes "
                        + creditor.getName()
                        + " : "
                        + balance.getAmount()
                );
            }
        }
    }

    public void showBalance(
            int userId) {

        if(!balanceRecord.containsKey(
                userId)) {

            System.out.println(
                    "No balances"
            );

            return;
        }

        User debtor =
                userRepository
                        .getUser(userId);

        for(Balance balance :
                balanceRecord
                        .get(userId)
                        .values()) {

            User creditor =
                    userRepository
                            .getUser(
                                    balance.getCreditorId()
                            );

            System.out.println(
                    debtor.getName()
                    + " owes "
                    + creditor.getName()
                    + " : "
                    + balance.getAmount()
            );
        }
    }

    // ----------------------------
    // HELPER
    // ----------------------------

    private HashMap<Integer, User>
    convertToMap(
            List<User> users) {

        HashMap<Integer, User> map =
                new HashMap<>();

        for(User user : users) {

            map.put(
                    user.getUserId(),
                    user
            );
        }

        return map;
    }
}