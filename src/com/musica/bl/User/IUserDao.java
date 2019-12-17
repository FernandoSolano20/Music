package com.musica.bl.User;

import com.musica.bl.Dao;

public interface IUserDao extends Dao<User> {
    boolean isAdminOnDB();
    User login(String email, String pass);
    User getUserById(int id);
    User getUserByEmail(String email);
}
