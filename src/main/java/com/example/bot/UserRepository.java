package com.example.bot;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static final String FILE_PATH = "src/main/data/userData.json";

    public void saveUser(UserData userData) {
        List<UserData> users = readUsers();
        users.add(userData);
        writeUsers(users);
    }

    public List<UserData> readUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            UserData[] userArray = gson.fromJson(reader, UserData[].class);
            if (userArray != null) {
                return Arrays.asList(userArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeUsers(List<UserData> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
