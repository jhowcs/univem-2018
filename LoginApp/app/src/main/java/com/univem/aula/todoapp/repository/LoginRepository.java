package com.univem.aula.todoapp.repository;

import com.univem.aula.todoapp.model.User;

public class LoginRepository {

    private static final String USER_FAKE = "adm";
    private static final String PASS_PAKE = "123";

    public boolean isUserExists(User user) {
        return user.getLogin().equalsIgnoreCase(USER_FAKE)
                && user.getPass().equalsIgnoreCase(PASS_PAKE);
    }
}
