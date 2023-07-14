package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User userA = new User("A", "7223023580", "A@gmail.com");
        User userB = new User("B", "7223023581", "B@gmail.com");
        User userC = new User("C", "7223023582", "C@gmail.com");
        User userD = new User("D", "7223023583", "D@gmail.com");
        User userE = new User("E", "7223023584", "E@gmail.com");

        ArrayList<String> sharedBetweenD1 = new ArrayList<>();
        sharedBetweenD1.add(userB.id);
        sharedBetweenD1.add(userC.id);
        sharedBetweenD1.add(userD.id);
        sharedBetweenD1.add(userE.id);


        ArrayList<String> sharedBetweenD2 = new ArrayList<>();
        sharedBetweenD2.add(userA.id);
//        sharedBetweenD2.add(userC.id);

        Expense d1 = new Expense("d1", 10000.00, userA.id, sharedBetweenD1);
//        Expense d2 = new Expense("d2", 1000.00, userB.id, sharedBetweenD2);


        ExpenseManagement expenseManagement = new ExpenseManagement();

        expenseManagement.add(d1);
//        expenseManagement.add(d2);

        ArrayList<Expense> x = expenseManagement.getExpenseList(userA.id);

        x.forEach( expense -> {
            System.out.println( expense.id + " " + expense.amount + " " + expense.userPaid + " " + expense.name);
        });



        System.out.println( "totalExpense:- " + userA.id + ":- " + expenseManagement.totalAmount(userA.id));
        System.out.println( "totalExpense:- " + userB.id + ":- " + expenseManagement.totalAmount(userB.id));

        expenseManagement.getExpenseDetails(userA.id);
        System.out.println("");
        expenseManagement.getExpenseDetails(userB.id);


    }
}