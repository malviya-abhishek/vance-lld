package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public String id;
    public String name;
    public String phoneNumber;
    public String email;
    public static HashMap<String, User> userHashMap = new HashMap<>();

    public ArrayList<String> expenseList;

    public User(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = "userId" + userHashMap.size();
        userHashMap.put(id, this);
        expenseList = new ArrayList<>();
    }
}
