package com.example.party6.data;

import com.example.party6.data.model.LoggedInUser;

import java.io.IOException;

public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        try {
            if (username.equals("admin") || username.equals("user")) {
                if (password.equals("pass")) {
                    LoggedInUser loggedInUser =
                            new LoggedInUser(java.util.UUID.randomUUID().toString(), username);
                    return new Result.Success<>(loggedInUser);
                } else {
                    return new Result.Error(new Exception("Неправильный пароль"));
                }
            } else {
                return new Result.Error(new Exception("Неправильное имя пользователя"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Ошибка входа", e));
        }
    }

    public void logout() {
        // Not implemented
    }
}
