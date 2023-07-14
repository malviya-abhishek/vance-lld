package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

public class ExpenseManagement {

    public HashMap<String, HashMap<String, Double>> expenseTable;

    public ExpenseManagement() {
        this.expenseTable = new HashMap<>();
    }

    public void add(Expense expense){
        int numberOfUsers = expense.sharedBetween.size() + 1;

        Double amount = expense.amount / numberOfUsers;
        User userPaid = User.userHashMap.get(expense.userPaid);

        userPaid.expenseList.add(expense.id);

        expense.sharedBetween.forEach( (userId) ->{
            // userPaid amount update
            HashMap<String, Double> userPaidAmountMap = expenseTable.get(userPaid.id);
            if(userPaidAmountMap == null)
                userPaidAmountMap = new HashMap<>();
            Double updatedAmount = amount;
            if(userPaidAmountMap.containsKey(userId))
                updatedAmount = userPaidAmountMap.get(userId) + amount;
            userPaidAmountMap.put(userId, updatedAmount);
            expenseTable.put(userPaid.id, userPaidAmountMap);

            //
            User userBorrowed = User.userHashMap.get(userId);
            userBorrowed.expenseList.add(expense.id);
            HashMap<String, Double> userBorrowedMap = expenseTable.get(userId);
            if(userBorrowedMap == null)
                userBorrowedMap = new HashMap<>();
            Double updateAmountUserBorrowed =  - amount;
            if(userBorrowedMap.containsKey(userPaid.id))
                updateAmountUserBorrowed = userBorrowedMap.get(userPaid.id) - amount;
            userBorrowedMap.put(userPaid.id, updateAmountUserBorrowed);
            expenseTable.put(userId, userBorrowedMap);
        });
    }


    public ArrayList<Expense> getExpenseList(String userId){
        ArrayList<Expense> res = new ArrayList<>();
        User.userHashMap.get(userId).expenseList.forEach( (a) -> {
            res.add(Expense.expensesHashMap.get(a));
        });
        return  res;
    }

    public Double totalAmount(String userId){
        final Double[] res = {0.0};
        HashMap<String, Double> borrowed = expenseTable.get(userId);
        if(borrowed!=null){
            borrowed.forEach(((s, aDouble) -> {
                res[0] = res[0] + aDouble;
            }));
        }
        return res[0];
    }


    public void getExpenseDetails(String userId){

        HashMap<String, Double> ls = expenseTable.get(userId);

        if(ls == null){
            System.out.println("No expenses");
            return;
        }

        ls.forEach(((s, aDouble) -> {
            if(aDouble != 0.0){
                String x = (aDouble > 0.0 ? "owes" : "to pay" );
                String res = userId + " " + x + " " + s + " " + Math.abs(aDouble);
                System.out.println(res);
            }
        }));

    }




}
