package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Expense {

    public String id;
    public String name;
    public Double amount;
    public String userPaid;
    public ArrayList<String> sharedBetween;
    public static HashMap<String, Expense> expensesHashMap = new HashMap<>();

    public Expense(String name, Double amount, String userPaid, ArrayList<String> sharedBetween) {
        this.name = name;
        this.amount = amount;
        this.userPaid = userPaid;
        this.sharedBetween = sharedBetween;
        this.id = "expenseId" + expensesHashMap.size();
        expensesHashMap.put(this.id, this);
    }
}
